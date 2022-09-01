package com.example.tz_meidasoft.DI

import android.content.Context
import com.example.tz_meidasoft.data.room.DaoCity
import com.example.tz_meidasoft.data.room.DatabaseCity
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    companion object{
        @Provides
        fun provideDaoCity(
            context: Context
        ):DaoCity{
            return DatabaseCity.getDatabaseCity(context).daoCity()
        }
    }

}