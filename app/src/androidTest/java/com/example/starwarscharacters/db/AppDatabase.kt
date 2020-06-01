package com.example.starwarscharacters.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.starwarscharacters.model.AppDataBase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class AppDatabase {

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    lateinit var appDataBase: AppDataBase

    @Before
    fun initDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDataBase = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java).build()
    }

    @After
    fun closeDb() {
        appDataBase.close()
    }
}