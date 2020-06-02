package com.example.starwarscharacters.utils

import com.example.starwarscharacters.model.People

object MockTestUtil {

    fun mockPeople() = People(
        id = 1,
        name = "Luke Skywalker",
        height = "172",
        mass = "77"
    )

    fun mockPeopleList() = mutableListOf(mockPeople())
}