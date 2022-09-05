package com.example.tz_meidasoft.DI

import android.content.Context
import com.example.tz_meidasoft.presentation.HomeActivity
import com.example.tz_meidasoft.presentation.TodayWeather.TodayWeatherFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: HomeActivity)

    fun injectToadyWeatherFragment(fragment: TodayWeatherFragment)

    @Component.Factory
    interface AppComponentBuilder{

        fun create(
            @BindsInstance context: Context
        ):AppComponent
    }
}