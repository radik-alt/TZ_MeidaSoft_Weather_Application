package com.example.tz_meidasoft.App

import android.app.Application
import com.example.tz_meidasoft.DI.AppComponent
import com.example.tz_meidasoft.DI.DaggerAppComponent
import javax.inject.Inject


class App : Application(){

//    @Inject
//    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

//        appComponent = DaggerAppComponent.builder().context(this).build()
    }

}
