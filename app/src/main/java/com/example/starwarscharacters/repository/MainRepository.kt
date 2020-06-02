package com.example.starwarscharacters.repository

import com.example.starwarscharacters.db.PeopleDao
import com.example.starwarscharacters.model.People
import com.example.starwarscharacters.network.MainApiService
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainRepository constructor(private val peopleDao: PeopleDao) : KoinComponent {

    private val mainApiService: MainApiService by inject()
    private val peopleList = peopleDao.getPeopleList()

    suspend fun getData() = when {
        peopleList.isEmpty() || peopleList.size == 0 -> dataFetchLogic()
        else -> peopleDao.getPeopleList()
    }

    private suspend fun dataFetchLogic(): MutableList<People> {

        for (x in 0 until 3) {
            println(" Data manipulation prior to REST API request if required $x")
        }

        val dataReceived = mainApiService.getPeopleData().results.toMutableList()
        peopleDao.insertPeopleList(dataReceived)

        for (x in 0 until 3) {
            println(" Data manipulation post REST API request if required $x")
        }
        return dataReceived
    }
}