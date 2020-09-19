package com.example.starwarscharacters.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.starwarscharacters.base.BaseTest
import com.example.starwarscharacters.db.PeopleDao
import com.example.starwarscharacters.di.configureTestAppComponent
import com.example.starwarscharacters.model.People
import com.example.starwarscharacters.utils.MockTestUtil.mockPeopleList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
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
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: MainRepository

    private val peopleDao = mockk<PeopleDao>(relaxed = true)

    private val mName = "Luke Skywalker"
    private val mHeight = "172"
    private val mMass = "77"

    // end region helper fields

    @Before
    fun setup() {
        super.setUp()
        startKoin { modules(configureTestAppComponent(getMockWebServerUrl())) }
    }

    @Test
    fun `mainRepo retrieveData success`() = runBlocking {

        mockNetworkResponseWithFileContent("success_resp_list", HttpURLConnection.HTTP_OK)

        every { peopleDao.getPeopleList() } returns mutableListOf()
        sut = MainRepository(peopleDao, createApi())

        val dataReceived = sut.getData()

        assertNotNull(dataReceived)
        assertEquals(dataReceived[0].name, mName)
        assertEquals(dataReceived[0].height, mHeight)
        assertEquals(dataReceived[0].mass, mMass)
    }

    @Test
    fun `mainRepo data from Dao success`() = runBlocking {

        val peopleList = mockPeopleList()
        every { peopleDao.getPeopleList() } returns peopleList

        sut = MainRepository(peopleDao, createApi())
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