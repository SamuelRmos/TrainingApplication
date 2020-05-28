package com.example.starwarscharacters.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwarscharacters.base.LiveDataWrapper
import com.example.starwarscharacters.model.People
import com.example.starwarscharacters.model.PeopleResult
import com.example.starwarscharacters.repository.MainRepository
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import java.lang.Exception

class MainViewModel(
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher,
    private val mainRepo: MainRepository
) : ViewModel(), KoinComponent {

    var mPeopleResponse = MutableLiveData<LiveDataWrapper<People>>()
    val mLoadingLiveData = MutableLiveData<Boolean>()
    private val job = SupervisorJob()
    private val mUiScope = CoroutineScope(mainDispatcher + job)
    private val mIoScope = CoroutineScope(ioDispatcher + job)

    fun requestData() {
        if (mPeopleResponse.value == null) {
            mUiScope.launch {
                mPeopleResponse.value = LiveDataWrapper.loading()
                setLoadingVisibility(true)
                try {
                    val data = mIoScope.async {
                        return@async mainRepo.getData()
                    }.await()

                    try {
                        mPeopleResponse.value = LiveDataWrapper.success(data)
                    } catch (e: Exception) {
                    }

                    setLoadingVisibility(false)
                } catch (e: Exception) {

                    setLoadingVisibility(false)
                    mPeopleResponse.value = LiveDataWrapper.error(e)
                }
            }
        }
    }

    private fun setLoadingVisibility(visible: Boolean) {
        mLoadingLiveData.postValue(visible)
    }

    override fun onCleared() {
        super.onCleared()
        this.job.cancel()
    }
}