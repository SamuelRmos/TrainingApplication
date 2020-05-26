package com.example.starwarscharacters.model

data class People(
    var name: String,
    var height: String,
    var mass: String
)

data class PeopleResult(
    var results: List<People>
)