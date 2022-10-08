package com.example.tz_meidasoft.presentation.adapter.AdapterToday

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tz_meidasoft.R
import com.example.tz_meidasoft.databinding.WeatherThreeBinding

class AdapterNextViewHolder(binding: WeatherThreeBinding) : RecyclerView.ViewHolder(binding.root) {

    val image = binding.imageWeatherNext
    val day = binding.dayNext
    val descriptionWeather = binding.descriptionWeatherNext
    val maxDegree = binding.maxDegreeNext
    val minDegree = binding.minDegreeNext

}