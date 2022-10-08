package com.example.tz_meidasoft.presentation.adapter.AdapterToday

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tz_meidasoft.R
import com.example.tz_meidasoft.data.entity.apiModel.ObjectTempAndWeather
import com.example.tz_meidasoft.databinding.WeatherThreeBinding
import kotlin.math.roundToInt

class AdapterTodayNextDays(private val list: ArrayList<ObjectTempAndWeather>) : RecyclerView.Adapter<AdapterNextViewHolder>() {

    private lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterNextViewHolder {
        context = parent.context
        val view = WeatherThreeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterNextViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterNextViewHolder, position: Int) {

        when (list[position].weather.description) {
            "пасмурно" -> {
                Glide.with(context).load(R.drawable.cloud_computing).into(holder.image)
            }
            "небольшой дождь" -> {
                Glide.with(context).load(R.drawable.rain).into(holder.image)
            }
            "дождь" -> {
                Glide.with(context).load(R.drawable.rain).into(holder.image)
            }
            else -> {
                Glide.with(context).load(R.drawable.ic_sun).into(holder.image)
            }
        }

        holder.day.text = "${list[position].day}: "
        holder.maxDegree.text = list[position].temp.max.roundToInt().toString()
        holder.minDegree.text = list[position].temp.min.roundToInt().toString()
        holder.descriptionWeather.text = list[position].weather.description
    }

    override fun getItemCount(): Int = list.size
}