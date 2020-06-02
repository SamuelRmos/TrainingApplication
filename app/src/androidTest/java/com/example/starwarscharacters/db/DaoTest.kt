package com.example.starwarscharacters.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.starwarscharacters.factory.BufferFactory
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DaoTest : AppDatabase() {

    private lateinit var peopleDao: PeopleDao

    @Before
    fun init() {
        peopleDao = appDataBase.peopleDao()
    }

    @Test
    fun peopleDao_insertPeople() {
        val cachedPeople = BufferFactory.makeCachedPeople()
        peopleDao.insertPeopleList(cachedPeople)

        val peopleList = peopleDao.getPeopleList()
        Assert.assertThat(peopleList[0].name, `is`(cachedPeople?.get(0)?.name))

        val people = peopleDao.getPeople(1)
        Assert.assertThat(people.name, `is`(cachedPeople?.get(0)?.name))
    }
}