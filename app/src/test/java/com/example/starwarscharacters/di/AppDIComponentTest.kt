package com.example.starwarscharacters.di

fun configureTestAppComponent(baseApi: String) =
    listOf(
        MockWebServerTest,
        configureApiDependencyTest(baseApi)
    )