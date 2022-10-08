package com.example.tz_meidasoft.presentation.adapter.AdapterNextDays

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tz_meidasoft.R
import com.example.tz_meidasoft.databinding.ItemNextDaysBinding

class ViewHolderNextDays(binding: ItemNextDaysBinding) : RecyclerView.ViewHolder(binding.root) {

    val day = binding.dayOfWeek
    val recycler = binding.insideNextDays

}