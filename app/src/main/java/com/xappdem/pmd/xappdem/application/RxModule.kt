package com.xappdem.pmd.xappdem.application

import com.xappdem.pmd.xappdem.schedulers.AppRxSchedulers
import com.xappdem.pmd.xappdem.schedulers.RxSchedulers
import dagger.Module
import dagger.Provides



/**
 * This module provides the RxScheduler dependencies
 * to be used for the networking concurrency logic
 */
@Module class RxModule {

    @Provides
    fun provideRxSchedulers(): RxSchedulers {
        return AppRxSchedulers()
    }
}