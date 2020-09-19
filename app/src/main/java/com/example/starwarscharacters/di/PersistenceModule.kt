package com.example.starwarscharacters.di

import androidx.room.Room
import com.example.starwarscharacters.R
import com.example.starwarscharacters.model.AppDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val PersistenceModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(), AppDataBase::class.java, androidApplication().getString(
                R.string.database
            )
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    factory { get<AppDataBase>().peopleDao() }
}