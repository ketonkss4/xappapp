package com.xappdem.pmd.xappdem.application

import android.app.Application

/**
 */
class GitApp : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        val appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}