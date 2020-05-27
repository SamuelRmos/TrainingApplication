package com.example.starwarscharacters.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.starwarscharacters.repository.MainRepository
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.IllegalArgumentException

class ViewModelFactory constructor(
    private val mainDispatcher: CoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher
): ViewModelProvider.Factory, KoinComponent {

    private val mMainRepository: MainRepository by inject()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(mainDispatcher, ioDispatcher,mMainRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel not configured")
        }
    }
}