package com.example.starwarscharacters.view

import android.os.Bundle
import com.example.starwarscharacters.base.BaseFragment

class MainFragment : BaseFragment(){

    companion object {
        fun getMainFragment() =
            MainFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}