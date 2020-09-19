package com.example.starwarscharacters.di

import com.example.starwarscharacters.view.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    factory { Dispatchers.Main }
    factory { Dispatchers.IO }

    viewModel {
        MainViewModel(
            mainDispatcher = get(),
            ioDispatcher = get(),
            mainRepo = get()
        )
    }

}