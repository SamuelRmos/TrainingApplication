package com.example.starwarscharacters.repository

import com.example.starwarscharacters.model.People
import com.example.starwarscharacters.network.MainApiService
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainRepository : KoinComponent {

    private val mainApiService: MainApiService by inject()

    suspend fun getData() = dataFetchLogic()

    private suspend fun dataFetchLogic(): MutableList<People> {

        for (x in 0 until 3) {
            println(" Data manipulation prior to REST API request if required $x")
        }

        val dataReceived = mainApiService.getPeopleData().results.toMutableList()

        for (x in 0 until 3) {
            println(" Data manipulation post REST API request if required $x")
        }
        return dataReceived
    }
}