package com.xappdem.pmd.xappdem.application

import android.content.Context
import com.xappdem.pmd.xappdem.MainActivity
import com.xappdem.pmd.xappdem.networking.ServiceClientHelper
import dagger.Component

/**
 */
@AppScope
@Component(modules = [AppModule::class])
interface AppComponent{
    fun inject(mainActivity : MainActivity)
    @AppScope fun getAppContext() : Context
    fun getService() : ServiceClientHelper.RepoService
}