package com.example.tz_meidasoft.presentation.adapter.AdapterNextDays

import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tz_meidasoft.R
import com.example.tz_meidasoft.data.entity.apiModel.ObjectTempAndWeather
import com.example.tz_meidasoft.domain.entity.apiDomain.ApiDomain
import com.example.tz_meidasoft.domain.entity.apiDomain.ListApi
import com.example.tz_meidasoft.presentation.adapter.AdapterNextDays.AdapterInside.AdapterInsideNextDays
import com.example.tz_meidasoft.presentation.adapter.AdapterToday.AdapterTodayNextDays
import java.util.*
import kotlin.collections.ArrayList

class AdapterNextDays(private val listResponse: List<ListApi>) : RecyclerView.Adapter<ViewHolderNextDays>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNextDays {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_next_days, parent, false)
        return ViewHolderNextDays(view)
    }

    override fun onBindViewHolder(holder: ViewHolderNextDays, position: Int) {

        val nowCalendar = Calendar.getInstance()
        nowCalendar.add(Calendar.DAY_OF_YEAR, position)
        Log.d("TAGDate", Calendar.FRIDAY.toString())

        if (position == 0){
            holder.day.text = "Сегодня"
        } else if (position == 1){
            holder.day.text = "Завтра"
        } else{
            holder.day.text = convertDate(nowCalendar.time)
        }

        val list = ArrayList<ObjectTempAndWeather>()
        list.add(ObjectTempAndWeather(morn, listResponse[position].temp, listResponse[position].weather[0]))
        list.add(ObjectTempAndWeather(day, listResponse[position].temp, listResponse[position].weather[0]))
        list.add(ObjectTempAndWeather(eve, listResponse[position].temp, listResponse[position].weather[0]))
        list.add(ObjectTempAndWeather(night, listResponse[position].temp, listResponse[position].weather[0]))
        holder.recycler.adapter = AdapterInsideNextDays(list)
    }

    private fun convertDate(date: Date): String = DateFormat.format("MM:dd:yyyy", date.time).toString()

    companion object{
        const val day = "Днем"
        const val eve = "Вечером"
        const val morn = "Утром"
        const val night = "Ночью"
    }

    override fun getItemCount(): Int = listResponse.size
}