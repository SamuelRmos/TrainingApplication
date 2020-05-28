package com.example.starwarscharacters.base

import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import java.io.BufferedReader
import java.io.Reader

abstract class BaseUITest : KoinTest {

    private lateinit var mockWebServer: MockWebServer

    private var mShouldStart = false

    @Before
    open fun setUp() {
        startMockServer()
    }

    fun mockNetworkResponseWithFileContent(fileName: String, responseCode: Int) =
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(getJson(fileName))
        )

    private fun getJson(path: String): String {
        var content = ""
        val testContext = InstrumentationRegistry.getInstrumentation().context
        val inputStream = testContext.assets.open(path)
        val reader = BufferedReader(inputStream.reader() as Reader)

        reader.use {
            content = it.readText()
        }
        return content
    }

    private fun startMockServer() {
        mShouldStart = true
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    fun getMockWebServerUrl() = mockWebServer.url("/").toString()

    private fun stopMockServer() {
        if (mShouldStart)
           mockWebServer.shutdown()
    }

    @After
    open fun tearDown() {
        stopMockServer()
        stopKoin()
    }
}