package com.example.starwarscharacters.di

import com.example.starwarscharacters.repository.MainRepository
import org.koin.dsl.module

val RepositoryModule = module {
    factory {
        MainRepository(get(), get())
    }
}