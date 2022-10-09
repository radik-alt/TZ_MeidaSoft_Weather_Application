package com.example.tz_meidasoft.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.tz_meidasoft.app.App
import com.example.tz_meidasoft.R
import com.example.tz_meidasoft.databinding.ActivityHomeBinding
import java.lang.RuntimeException

class HomeActivity : AppCompatActivity() {

    private var _binding : ActivityHomeBinding? = null
    private val binding: ActivityHomeBinding
        get() = _binding ?: throw RuntimeException("ActivityHomeBinding == null")


    private val component by lazy{
        (application as App).component
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        component.inject(this)
    }


}