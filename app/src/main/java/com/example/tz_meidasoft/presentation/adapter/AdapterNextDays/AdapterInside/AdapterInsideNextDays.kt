package com.example.tz_meidasoft.presentation.adapter.AdapterNextDays.AdapterInside

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tz_meidasoft.R
import com.example.tz_meidasoft.data.entity.apiModel.ObjectTempAndWeather
import com.example.tz_meidasoft.databinding.ItemInsideNextDaysBinding
import com.example.tz_meidasoft.presentation.adapter.AdapterNextDays.AdapterNextDays
import kotlin.math.roundToInt

class AdapterInsideNextDays(private val listInside: List<ObjectTempAndWeather>) : RecyclerView.Adapter<ViewHolderInsideNextDays>() {

    private lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderInsideNextDays {
        context = parent.context
        val view = ItemInsideNextDaysBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderInsideNextDays(view)
    }

    override fun onBindViewHolder(holder: ViewHolderInsideNextDays, position: Int) {

        when (listInside[position].weather.description) {
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

        when (listInside[position].day) {
            AdapterNextDays.day -> {
                holder.maxDegreeNext.text = listInside[position].temp.day.roundToInt().toString()
            }
            AdapterNextDays.morn -> {
                holder.maxDegreeNext.text = listInside[position].temp.morn.roundToInt().toString()
            }
            AdapterNextDays.eve -> {
                holder.maxDegreeNext.text = listInside[position].temp.eve.roundToInt().toString()
            }
            AdapterNextDays.night -> {
                holder.maxDegreeNext.text = listInside[position].temp.night.roundToInt().toString()
            }
            else -> {
                throw (Exception("AdapterInsideNextDays error with listInside (don't found data)"))
            }
        }

        holder.description.text = listInside[position].weather.description
        holder.timeOfDay.text = listInside[position].day

    }

    override fun getItemCount(): Int = listInside.size
}