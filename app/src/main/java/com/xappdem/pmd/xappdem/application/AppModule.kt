package com.xappdem.pmd.xappdem.application

import android.content.Context
import com.xappdem.pmd.xappdem.networking.ServiceClientHelper
import dagger.Module
import dagger.Provides

/**
 * This class defines the dependencies that will be provided
 * by dependency injection via dagger. This module provides
 * application context and service dependencies
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