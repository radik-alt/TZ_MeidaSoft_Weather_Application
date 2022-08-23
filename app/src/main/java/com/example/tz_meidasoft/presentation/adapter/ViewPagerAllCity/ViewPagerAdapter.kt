package com.example.tz_meidasoft.presentation.adapter.ViewPagerAllCity

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tz_meidasoft.R
import com.example.tz_meidasoft.data.entity.apiModel.ApiModel
import com.example.tz_meidasoft.domain.entity.CityDomain

class ViewPagerAdapter(
    private val listCity: List<CityDomain>,
//    private val response:ApiModel
): RecyclerView.Adapter<ViewHolderPagerCity>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPagerCity {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_viewpager, parent, false)
        return ViewHolderPagerCity(view)
    }

    override fun onBindViewHolder(holder: ViewHolderPagerCity, position: Int) {
        Log.d("ResponseAdapterPager", position.toString())
    }

    override fun getItemCount(): Int = listCity.size
}