package com.example.tz_meidasoft.presentation.adapter.AdapterNextDays

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

    private var nowCalendar = Calendar.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNextDays {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_next_days, parent, false)
        return ViewHolderNextDays(view)
    }

    override fun onBindViewHolder(holder: ViewHolderNextDays, position: Int) {

        nowCalendar.add(Calendar.DAY_OF_YEAR, position)
        Log.d("TAGDate", Calendar.FRIDAY.toString())

        holder.day.text = nowCalendar.time.date.toString()

        val list = ArrayList<ObjectTempAndWeather>()
        list.add(ObjectTempAndWeather(morn, listResponse[position].temp, listResponse[position].weather[0]))
        list.add(ObjectTempAndWeather(day, listResponse[position].temp, listResponse[position].weather[0]))
        list.add(ObjectTempAndWeather(eve, listResponse[position].temp, listResponse[position].weather[0]))
        list.add(ObjectTempAndWeather(night, listResponse[position].temp, listResponse[position].weather[0]))
        holder.recycler.adapter = AdapterInsideNextDays(list)
    }

    companion object{
        const val day = "Днем"
        const val eve = "Вечером"
        const val morn = "Утром"
        const val night = "Ночью"
    }

    override fun getItemCount(): Int = listResponse.size
}