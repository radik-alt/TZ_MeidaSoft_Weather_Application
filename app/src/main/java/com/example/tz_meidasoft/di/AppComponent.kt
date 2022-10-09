package com.example.tz_meidasoft.di

import android.app.Application
import android.content.Context
import com.example.tz_meidasoft.presentation.HomeActivity
import com.example.tz_meidasoft.presentation.todayWeather.TodayWeatherFragment
import com.example.tz_meidasoft.presentation.сity.ChooseCityFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: HomeActivity)

    fun inject(fragment: TodayWeatherFragment)

    fun inject(fragment: ChooseCityFragment)

    @Component.Factory
    interface AppComponentBuilder{

        fun create(
            @BindsInstance context: Application
        ):AppComponent
    }
}