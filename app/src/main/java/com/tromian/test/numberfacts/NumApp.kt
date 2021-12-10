package com.tromian.test.numberfacts

import android.app.Application
import android.content.Context
import com.tromian.test.numberfacts.di.AppComponent
import com.tromian.test.numberfacts.di.DaggerAppComponent

class NumApp : Application(){

    private var _appComponent: AppComponent? = null
    internal val appComponent: AppComponent
        get() = checkNotNull(_appComponent) {
            "AppComponent isn't initialized"
        }

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.factory().create(
            this
        )
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is NumApp -> appComponent
        else -> this.applicationContext.appComponent
    }