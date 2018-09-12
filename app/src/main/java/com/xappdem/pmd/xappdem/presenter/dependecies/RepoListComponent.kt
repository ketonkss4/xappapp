package com.xappdem.pmd.xappdem.presenter.dependecies

import com.xappdem.pmd.xappdem.MainActivity
import com.xappdem.pmd.xappdem.application.AppComponent
import dagger.Component

/**
 */
@RepoListScope
@Component(dependencies = [AppComponent::class], modules = [RepoListModule::class])
interface RepoListComponent{
    fun inject (mainActivity: MainActivity)
}
