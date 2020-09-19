package com.example.starwarscharacters.network

import com.example.starwarscharacters.model.PeopleResult
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface MainApiService {
    @GET("people/?page=1")
    suspend fun getPeopleDataAsync(): Response<PeopleResult>
}