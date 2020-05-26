package com.example.starwarscharacters.network

import com.example.starwarscharacters.model.PeopleResult
import retrofit2.http.GET

interface MainApiService {
    @GET("people/1")
    suspend fun getPeopleData(): PeopleResult
}