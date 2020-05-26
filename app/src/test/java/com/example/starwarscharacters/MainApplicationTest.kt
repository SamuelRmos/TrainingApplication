package com.example.starwarscharacters

import org.koin.core.module.Module

class MainApplicationTest: MainApplication() {

    //region constants

    //end region constants

    //region helper fields

    // end region helper fields
    override fun provideDependency() = listOf<Module>()

    // region helper methods

    // end region helper methods

    // region helper class

    // end region helper class
}