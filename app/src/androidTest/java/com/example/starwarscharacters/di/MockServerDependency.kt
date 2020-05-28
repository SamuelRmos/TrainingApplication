package com.example.starwarscharacters.di

import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module

val MockServerTest = module {
    factory {
        MockWebServer()
    }
}