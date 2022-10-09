package com.example.tz_meidasoft.di

import android.app.Application
import android.content.Context
import com.example.tz_meidasoft.data.room.DaoCity
import com.example.tz_meidasoft.data.room.DatabaseCity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface DataModule {

    companion object{
        @Singleton
        @Provides
        fun provideDatabaseCity(
            context: Application
        ):DatabaseCity = DatabaseCity.getDatabaseCity(context.applicationContext)


        @Singleton
        @Provides
        fun provideDaoCity(
            databaseCity: DatabaseCity
        ): DaoCity = databaseCity.daoCity()
    }


}