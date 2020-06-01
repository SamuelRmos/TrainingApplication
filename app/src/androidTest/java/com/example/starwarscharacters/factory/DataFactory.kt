package com.example.starwarscharacters.factory

import com.example.starwarscharacters.model.People

class DataFactory {

    companion object {
        fun randomList(): MutableList<People>? {
            val people = People(null, "Luke Skywalker", "77", "182")
            val list = mutableListOf<People>()
            list.add(people)
            return list
        }
    }
}