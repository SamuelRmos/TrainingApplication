package com.example.starwarscharacters.repository

import com.example.starwarscharacters.model.People

interface Repository {

    suspend fun getData(): MutableList<People>

    suspend fun dataFetchLogic(): MutableList<People>

}