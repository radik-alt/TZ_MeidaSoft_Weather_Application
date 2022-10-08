package com.example.tz_meidasoft.di

import android.content.Context
import com.example.tz_meidasoft.presentation.HomeActivity
import com.example.tz_meidasoft.presentation.todayWeather.TodayWeatherFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: HomeActivity)

    fun inject(fragment: TodayWeatherFragment)

    @Component.Factory
    interface AppComponentBuilder{

        fun create(
            @BindsInstance context: Context
        ):AppComponent
    }
}