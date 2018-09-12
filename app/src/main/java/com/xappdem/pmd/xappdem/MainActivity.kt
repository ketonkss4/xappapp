package com.xappdem.pmd.xappdem

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.xappdem.pmd.xappdem.application.GitApp
import com.xappdem.pmd.xappdem.data.RepoResult
import com.xappdem.pmd.xappdem.presenter.RepoListPresenter
import com.xappdem.pmd.xappdem.presenter.dependecies.DaggerRepoListComponent
import com.xappdem.pmd.xappdem.presenter.dependecies.RepoListModule
import javax.inject.Inject


/**
 */
class MainActivity : AppCompatActivity(), RepoListPresenter.RepoListView {
    @Inject lateinit var repoListPresenter: RepoListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerRepoListComponent.builder()
                .appComponent((application as GitApp).appComponent)
                .repoListModule(RepoListModule(this))
                .build()
                .inject(this)
        setContentView(R.layout.repo_list_view)

        repoListPresenter.onCreate()
    }

    override fun onListDataResponse(list: List<RepoResult>) {
        Log.v(MainActivity::class.simpleName, "On List Data Response")
    }

    override fun onErrorResponse(errorMsg: String) {
        Log.v(MainActivity::class.simpleName, "On Error - $errorMsg")
    }
}