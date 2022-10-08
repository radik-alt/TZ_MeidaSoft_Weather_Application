package com.example.tz_meidasoft.presentation.adapter.AdapterNextDays.AdapterInside

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tz_meidasoft.R
import com.example.tz_meidasoft.databinding.ItemInsideNextDaysBinding

class ViewHolderInsideNextDays(binding: ItemInsideNextDaysBinding) : RecyclerView.ViewHolder(binding.root) {

    val timeOfDay = binding.timeOfDay
    val maxDegreeNext = binding.maxDegreeNext
    val image = binding.imageWeatherNext
    val description = binding.weatherDescription
}