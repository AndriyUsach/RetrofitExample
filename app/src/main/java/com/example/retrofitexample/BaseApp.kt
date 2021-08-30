package com.example.retrofitexample

import android.app.Application
import android.content.Context
import com.example.retrofitexample.di.ApplicationComponent
import com.example.retrofitexample.di.DaggerApplicationComponent

class BaseApp : Application() {

    var appComponent: ApplicationComponent? = null
    private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.create()
    }
}

val Context.appComponent: ApplicationComponent
    get() = when (this) {
        is BaseApp -> appComponent!!
        else -> applicationContext.appComponent
    }