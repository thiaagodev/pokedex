package com.thiaagodev.pokedex.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.thiaagodev.pokedex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun hideToolbar() {
        binding.mainToolbar.visibility = View.GONE
    }

    fun showToolbar() {
        binding.mainToolbar.visibility = View.VISIBLE
    }
}