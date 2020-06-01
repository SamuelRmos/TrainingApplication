package com.example.starwarscharacters.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.starwarscharacters.db.PeopleDao

@Database(entities = [People::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun peopleDao(): PeopleDao
}