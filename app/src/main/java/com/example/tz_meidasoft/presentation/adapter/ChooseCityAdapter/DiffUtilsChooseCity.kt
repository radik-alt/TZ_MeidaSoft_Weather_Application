package com.example.tz_meidasoft.presentation.adapter.ChooseCityAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.tz_meidasoft.data.entity.dbModel.City
import com.example.tz_meidasoft.domain.entity.CityDomain

class DiffUtilsChooseCity(
    private val oldList: ArrayList<CityDomain>,
    private val newList: ArrayList<CityDomain>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList == newList
    }
}