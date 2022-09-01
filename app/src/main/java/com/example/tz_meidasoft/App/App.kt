package com.example.tz_meidasoft.App

import android.app.Application
import com.example.tz_meidasoft.DI.AppComponent
import com.example.tz_meidasoft.DI.DaggerAppComponent
import javax.inject.Inject


class App : Application(){

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

}
