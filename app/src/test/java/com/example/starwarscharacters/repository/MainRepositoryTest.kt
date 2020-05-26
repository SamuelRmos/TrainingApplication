package com.example.starwarscharacters.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.starwarscharacters.base.BaseTest
import com.example.starwarscharacters.di.configureTestAppComponent
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class MainRepositoryTest : BaseTest() {
    //region constants

    //end region constants

    //region helper fields
    private lateinit var sut: MainRepository

    private val mName = "Luke Skywalker"
    private val mHeight = "172"
    private val mMass = "77"

    // end region helper fields

    @Before
    fun setup() {
        super.setUp()
        startKoin { modules(configureTestAppComponent(getMockWebServerUrl())) }
        sut = MainRepository()
    }

    @Test
    fun mainRepo_retrieveSuccess_data() = runBlocking {
        mockNetworkResponseWithFileContent("success_resp_list", HttpURLConnection.HTTP_OK)
        val dataReceived = sut.getData()

        assertNotNull(dataReceived)
        assertEquals(dataReceived[0].name, mName)
        assertEquals(dataReceived[0].height, mHeight)
        assertEquals(dataReceived[0].mass, mMass)
    }

    // region helper methods

    // end region helper methods

    // region helper class

    // end region helper class
}