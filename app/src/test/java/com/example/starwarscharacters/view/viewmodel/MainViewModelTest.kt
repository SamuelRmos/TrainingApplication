@file:Suppress("DEPRECATION")

package com.example.starwarscharacters.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.starwarscharacters.base.BaseTest
import com.example.starwarscharacters.base.LiveDataWrapper
import com.example.starwarscharacters.di.configureTestAppComponent
import com.example.starwarscharacters.model.PeopleResult
import com.example.starwarscharacters.repository.MainRepository
import com.google.gson.Gson
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin

@RunWith(JUnit4::class)
class MainViewModelTest : BaseTest() {
    //region constants

    //end region constants

    //region helper fields

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mDispatcher = Dispatchers.Unconfined

    private lateinit var sut: MainViewModel
    private val mName = "Luke Skywalker"

    @MockK
    lateinit var mMainRepository: MainRepository
    // end region helper fields

    @Before
    fun setup() {
        super.setUp()
        MockKAnnotations.init(this)
        startKoin { modules(configureTestAppComponent(getMockWebServerUrl())) }
        sut = MainViewModel(mDispatcher, mDispatcher, mMainRepository)
    }

    @Test
    fun mainViewModel_dataPopulates_expectedValue() = runBlocking {
        val sampleResponse = getJson("success_resp_list")
        val jsonObj = Gson().fromJson(sampleResponse, PeopleResult::class.java)

        coEvery { mMainRepository.getData() } returns jsonObj.results.toMutableList()
        sut.mPeopleResponse.observeForever { }

        sut.requestData()

        assertNotNull(sut.mPeopleResponse.value)
        assertEquals(
            sut.mPeopleResponse.value!!.responseStatus,
            LiveDataWrapper.ResponseStatus.SUCCESS
        )

        val testResult = sut.mPeopleResponse.value

        assertEquals(testResult!!.response?.get(0)?.name, mName)
    }

    // region helper methods

    // end region helper methods

    // region helper class

    // end region helper class
}