package com.xappdem.pmd.xappdem.presenter.dependecies

import com.xappdem.pmd.xappdem.MainActivity
import com.xappdem.pmd.xappdem.application.AppComponent
import dagger.Component

/**
 * This @component class provides the blueprint for dagger generated code
 * pertaining to getting and displaying the repo lists data
 */
@RepoListScope
@Component(dependencies = [AppComponent::class], modules = [RepoListModule::class])
interface RepoListComponent{
    fun inject (mainActivity: MainActivity)
}
