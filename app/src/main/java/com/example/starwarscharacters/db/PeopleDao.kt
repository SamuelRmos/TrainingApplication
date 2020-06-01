package com.example.starwarscharacters.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarscharacters.model.People

@Dao
abstract class PeopleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPeopleList(people: MutableList<People>?)

    @Query("SELECT * FROM People WHERE id = :id_")
    abstract fun getPeople(id_: Int): People

    @Query("SELECT * FROM People")
    abstract fun getPeopleList(): MutableList<People>
}