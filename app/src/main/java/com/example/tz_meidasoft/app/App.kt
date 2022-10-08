package com.example.tz_meidasoft.app

import android.app.Application
import com.example.tz_meidasoft.di.DaggerAppComponent


class App : Application(){

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

}
