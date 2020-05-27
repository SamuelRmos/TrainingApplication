package com.example.starwarscharacters.di

import com.example.starwarscharacters.repository.MainRepository
import org.koin.dsl.module

val RepositoryDependency = module {
    factory {
        MainRepository()
    }
}