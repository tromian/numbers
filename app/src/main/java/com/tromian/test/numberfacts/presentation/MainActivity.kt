package com.tromian.test.numberfacts.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tromian.test.numberfacts.R
import com.tromian.test.numberfacts.appComponent
import com.tromian.test.numberfacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

    }
}