package com.example.starwarscharacters.di

fun generateTestAppComponent(baseApi: String) =
    listOf(configureNetworkForInstrumentationTest(baseApi), MockServerTest, RepositoryDependency)