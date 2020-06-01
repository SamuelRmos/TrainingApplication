package com.example.starwarscharacters.factory

import com.example.starwarscharacters.model.People

class BufferFactory {
    companion object {
        fun makeCachedPeople() = DataFactory.randomList()
    }
}