package com.example.starwarscharacters.app

import com.example.starwarscharacters.MainApplication
import org.koin.core.module.Module

class TestMainApplication : MainApplication() {
    override fun provideDependency() = listOf<Module>()
}