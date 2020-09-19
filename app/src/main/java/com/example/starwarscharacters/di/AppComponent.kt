package com.example.starwarscharacters.di

val appComponent = listOf(
    ApiModule,
    RepositoryModule,
    PersistenceModule,
    AppModule
)