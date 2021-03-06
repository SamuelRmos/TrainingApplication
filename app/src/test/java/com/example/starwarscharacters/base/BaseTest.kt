package com.example.starwarscharacters.base

import com.example.starwarscharacters.network.MainApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

abstract class BaseTest : KoinTest {

    private lateinit var mMockServerInstance: MockWebServer
    private var mShouldStart = false

    @Before
    open fun setUp() {
        startMockServer()
    }

    fun mockNetworkResponseWithFileContent(fileName: String, responseCode: Int) =
        mMockServerInstance.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(getJson(fileName))
        )

    fun getJson(path: String): String {
        val uri = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

    private fun startMockServer() {
        mShouldStart = true
        mMockServerInstance = MockWebServer()
        mMockServerInstance.start()
    }

    fun getMockWebServerUrl() = mMockServerInstance.url("/").toString()

    fun createApi(): MainApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(getMockWebServerUrl())
        .build()
        .create(MainApiService::class.java)

    private fun stopMockServer() {
        if (mShouldStart)
            mMockServerInstance.shutdown()
    }

    @After
    open fun tearDown() {
        stopMockServer()
        stopKoin()
    }
}