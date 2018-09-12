package com.xappdem.pmd.xappdem.presenter.dependecies

import com.xappdem.pmd.xappdem.MainActivity
import com.xappdem.pmd.xappdem.networking.ServiceClientHelper
import com.xappdem.pmd.xappdem.presenter.RepoDataProvider
import com.xappdem.pmd.xappdem.presenter.RepoListPresenter
import com.xappdem.pmd.xappdem.schedulers.RxSchedulers
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class RepoListModule(val mainActivity: MainActivity) {

    @Provides
    fun providePresenter(
            rxSchedulers: RxSchedulers,
            repoDataProvider: RepoDataProvider,
            repoListView: RepoListPresenter.RepoListView
    ): RepoListPresenter = RepoListPresenter(
            rxSchedulers,
            repoDataProvider,
            repoListView,
            CompositeDisposable()
    )

    @Provides
    fun providesRepoListView(): RepoListPresenter.RepoListView = mainActivity

    @Provides
    fun provideRepoData(service: ServiceClientHelper.RepoService): RepoDataProvider =
            RepoDataProvider(service)

}
