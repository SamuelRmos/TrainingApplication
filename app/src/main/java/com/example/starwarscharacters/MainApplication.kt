package com.example.starwarscharacters

import android.app.Application
import com.example.starwarscharacters.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(provideDependency())
        }
    }

    open fun provideDependency() = appComponent
}