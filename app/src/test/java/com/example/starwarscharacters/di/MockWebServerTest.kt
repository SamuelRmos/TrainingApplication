package com.example.starwarscharacters.di

import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module

val MockWebServerTest = module {
    factory {
        MockWebServer()
    }
}