package com.example.starwarscharacters.di

import com.example.starwarscharacters.network.MainApiService
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun configureApiDependencyTest(baseApi: String) =
    module {
        single {
            Retrofit.Builder()
                .baseUrl(baseApi)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }
        factory { get<Retrofit>().create(MainApiService::class.java) }
    }