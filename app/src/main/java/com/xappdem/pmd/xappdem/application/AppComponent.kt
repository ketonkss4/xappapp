package com.xappdem.pmd.xappdem.application

import com.xappdem.pmd.xappdem.networking.ServiceClientHelper
import com.xappdem.pmd.xappdem.schedulers.RxSchedulers
import dagger.Component

/**
 */
@AppScope
@Component(modules = [AppModule::class, RxModule::class])
interface AppComponent {
    fun rxSchedulers() : RxSchedulers
    fun service(): ServiceClientHelper.RepoService
}