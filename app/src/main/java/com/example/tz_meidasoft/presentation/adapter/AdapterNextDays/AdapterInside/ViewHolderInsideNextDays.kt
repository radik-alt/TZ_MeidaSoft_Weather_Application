package com.example.tz_meidasoft.presentation.adapter.AdapterNextDays.AdapterInside

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tz_meidasoft.R

class ViewHolderInsideNextDays(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val timeOfDay = itemView.findViewById<TextView>(R.id.timeOfDay)
    val maxDegreeNext = itemView.findViewById<TextView>(R.id.maxDegreeNext)
    val minDegreeNext = itemView.findViewById<TextView>(R.id.minDegreeNext)
    val image = itemView.findViewById<ImageView>(R.id.imageWeatherNext)
    val description = itemView.findViewById<TextView>(R.id.weatherDescription)

}