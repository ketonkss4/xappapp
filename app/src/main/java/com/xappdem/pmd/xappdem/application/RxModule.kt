package com.xappdem.pmd.xappdem.application

import com.xappdem.pmd.xappdem.schedulers.AppRxSchedulers
import com.xappdem.pmd.xappdem.schedulers.RxSchedulers
import dagger.Module
import dagger.Provides



/**
 */
@Module class RxModule {

    @Provides
    fun provideRxSchedulers(): RxSchedulers {
        return AppRxSchedulers()
    }
}