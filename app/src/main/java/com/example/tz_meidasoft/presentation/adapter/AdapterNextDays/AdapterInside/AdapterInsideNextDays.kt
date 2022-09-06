package com.example.tz_meidasoft.presentation.adapter.AdapterNextDays.AdapterInside

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tz_meidasoft.R
import com.example.tz_meidasoft.data.entity.apiModel.ObjectTempAndWeather
import com.example.tz_meidasoft.presentation.adapter.AdapterNextDays.AdapterNextDays
import kotlin.math.roundToInt

class AdapterInsideNextDays(private val listInside: List<ObjectTempAndWeather>) : RecyclerView.Adapter<ViewHolderInsideNextDays>() {

    private lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderInsideNextDays {
        context = parent.context
        return ViewHolderInsideNextDays(LayoutInflater.from(parent.context).inflate(R.layout.item_inside_next_days, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderInsideNextDays, position: Int) {

        if (listInside[position].weather.description == "пасмурно"){
            Glide.with(context).load(R.drawable.cloud_computing).into(holder.image)
        } else if (listInside[position].weather.description == "небольшой дождь"){
            Glide.with(context).load(R.drawable.rain).into(holder.image)
        } else if(listInside[position].weather.description == "дождь"){
            Glide.with(context).load(R.drawable.rain).into(holder.image)
        } else {
            Glide.with(context).load(R.drawable.ic_sun).into(holder.image)
        }

        if (listInside[position].day == AdapterNextDays.day){
            holder.maxDegreeNext.text = listInside[position].temp.day.roundToInt().toString()
        } else if (listInside[position].day == AdapterNextDays.morn){
            holder.maxDegreeNext.text = listInside[position].temp.morn.roundToInt().toString()
        } else if (listInside[position].day == AdapterNextDays.eve){
            holder.maxDegreeNext.text = listInside[position].temp.eve.roundToInt().toString()
        } else if (listInside[position].day == AdapterNextDays.night) {
            holder.maxDegreeNext.text = listInside[position].temp.night.roundToInt().toString()
        } else {
            throw (Exception("AdapterInsideNextDays error with listInside (don't found data)"))
        }

        holder.description.text = listInside[position].weather.description
        holder.timeOfDay.text = listInside[position].day

    }

    override fun getItemCount(): Int = listInside.size
}