package com.example.tz_meidasoft.presentation.adapter.AdapterNextDays

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tz_meidasoft.R

class ViewHolderNextDays(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val day = itemView.findViewById<TextView>(R.id.dayOfWeek)
    val recycler = itemView.findViewById<RecyclerView>(R.id.insideNextDays)

}