package com.example.starwarscharacters.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.starwarscharacters.R
import com.example.starwarscharacters.databinding.ActivityMainBinding
import com.example.starwarscharacters.view.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showFragment()
    }

    private fun showFragment() {
        supportFragmentManager.findFragmentById(R.id.frame_layout) as MainFragment?
        supportFragmentManager.beginTransaction()
            .add(
                R.id.frame_layout,
                MainFragment.getMainFragment()
            )
            .commit()
    }
}
