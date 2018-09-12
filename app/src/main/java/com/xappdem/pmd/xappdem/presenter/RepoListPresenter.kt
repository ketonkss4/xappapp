package com.xappdem.pmd.xappdem.presenter

import com.xappdem.pmd.xappdem.data.RepoResult
import com.xappdem.pmd.xappdem.schedulers.RxSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 */
class RepoListPresenter(
        private val rxSchedulers : RxSchedulers,
        private val repoDataProvider: RepoDataProvider,
        private val repoListView: RepoListView,
        private val compositeDisposable: CompositeDisposable
) {
    interface RepoListView {
        fun onListDataResponse(list: List<RepoResult>)
        fun onErrorResponse(errorMsg: String)
    }

    fun onCreate(){
        compositeDisposable.add(requestRepoListData())
    }

    private fun requestRepoListData(): Disposable {
        val requestCall = repoDataProvider.provideRepoList()
        return requestCall.subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.uiThread())
                .subscribe({ list ->
                    repoListView.onListDataResponse(list)
                }, {error ->
                    error.message?.let { repoListView.onErrorResponse(it) }
                })

    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}