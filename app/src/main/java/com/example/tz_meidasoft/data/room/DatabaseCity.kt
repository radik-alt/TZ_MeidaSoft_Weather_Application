package com.example.tz_meidasoft.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tz_meidasoft.data.entity.dbModel.CityEntity

@Database(entities = [CityEntity::class], version = 1, exportSchema = false)
abstract class DatabaseCity : RoomDatabase() {

    abstract fun daoCity(): DaoCity

    companion object {
        @Volatile
        var INSTANCE: DatabaseCity? = null

        fun getDatabaseCity(context: Context): DatabaseCity {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val roomDataBaseInstance = Room.databaseBuilder(
                    context,
                    DatabaseCity::class.java,
                    "CityEntity"
                )
                .allowMainThreadQueries()
                .build()

                INSTANCE = roomDataBaseInstance
                return roomDataBaseInstance
            }
        }
    }
}