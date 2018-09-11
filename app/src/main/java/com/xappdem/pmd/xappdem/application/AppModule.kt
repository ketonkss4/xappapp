package com.xappdem.pmd.xappdem.application

import android.content.Context
import com.xappdem.pmd.xappdem.networking.ServiceClientHelper
import dagger.Module
import dagger.Provides

/**
 */
@Module
class AppModule(private val context: Context) {

    @Provides
    @AppScope
    fun getAppContext(): Context {
        return context.applicationContext
    }

    @Provides fun provideClient(): ServiceClientHelper.RepoService {
        return ServiceClientHelper().buildService()
    }

}