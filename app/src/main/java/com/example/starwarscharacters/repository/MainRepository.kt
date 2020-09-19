package com.example.starwarscharacters.repository

import com.example.starwarscharacters.base.BaseRepository
import com.example.starwarscharacters.db.PeopleDao
import com.example.starwarscharacters.model.People
import com.example.starwarscharacters.network.MainApiService

class MainRepository(
    private val peopleDao: PeopleDao,
    private val mainApiService: MainApiService
) : BaseRepository(), Repository {

    private val peopleList = peopleDao.getPeopleList()

    override suspend fun getData() = when (peopleList.size) {
        0 -> dataFetchLogic()
        else -> peopleDao.getPeopleList()
    }

    override suspend fun dataFetchLogic(): MutableList<People> {
        val data = safeApiCall(
            call = { mainApiService.getPeopleDataAsync() },
            errorMessage = "Error Fetching Data from API"
        )

        val dataReceived = data?.results!!.toMutableList()
        peopleDao.insertPeopleList(dataReceived)

        return dataReceived
    }

}