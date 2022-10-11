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
        @Provides
        @Singleton
        fun provideDaoCity(
            databaseCity: DatabaseCity
        ): DaoCity = databaseCity.daoCity()


        @Provides
        @Singleton
        fun provideDatabaseCity(
            context: Application
        ):DatabaseCity = DatabaseCity.getDatabaseCity(context)
    }

}