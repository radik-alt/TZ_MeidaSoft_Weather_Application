package com.example.tz_meidasoft.data.entity.dbModel

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val city: String,
    val used: Boolean
)
