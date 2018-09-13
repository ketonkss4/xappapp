package com.xappdem.pmd.xappdem.presenter

import com.xappdem.pmd.xappdem.data.RepoResult
import com.xappdem.pmd.xappdem.schedulers.RxSchedulers
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * This is a presenter for a RepoListView that contains
 * logic for making list data requests and passing the response
 * to the view
 */
class RepoListPresenter(
        private val rxSchedulers: RxSchedulers,
        private val repoDataProvider: RepoDataProvider,
        private val repoListView: RepoListView,
        private val compositeDisposable: CompositeDisposable
) {
    interface RepoListView {
        fun onListDataResponse(list: List<RepoResult>)
        fun onErrorResponse(errorMsg: String)
    }

    fun requestRepoList() {
        compositeDisposable.add(requestRepoListData())
    }

    private fun requestRepoListData(): Disposable {
        val requestCall = repoDataProvider.provideRepoList()
        return requestCall.subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.uiThread())
                .subscribe({ list ->
                    repoListView.onListDataResponse(list)
                }, { error ->
                    error.message?.let { repoListView.onErrorResponse(it) }
                })
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}