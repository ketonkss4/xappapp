package com.xappdem.pmd.xappdem.application

import com.xappdem.pmd.xappdem.networking.ServiceClientHelper
import com.xappdem.pmd.xappdem.schedulers.RxSchedulers
import dagger.Component

/**
 * This interface provides the blueprint for the dagger library
 * to create generated code based on the dependencies specified
 * in the @module classes.
 * Bindings are specified in the below functions to be accessed
 * by dependencies of this module
 */
@AppScope
@Component(modules = [AppModule::class, RxModule::class])
interface AppComponent {
    fun rxSchedulers() : RxSchedulers
    fun service(): ServiceClientHelper.RepoService
}