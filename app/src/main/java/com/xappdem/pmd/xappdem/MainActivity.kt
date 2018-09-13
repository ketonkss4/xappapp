package com.xappdem.pmd.xappdem

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.xappdem.pmd.xappdem.RepoDetailActivity.Companion.REPO_KEY
import com.xappdem.pmd.xappdem.adapter.RepoListAdapter
import com.xappdem.pmd.xappdem.application.GitApp
import com.xappdem.pmd.xappdem.data.RepoResult
import com.xappdem.pmd.xappdem.presenter.RepoListPresenter
import com.xappdem.pmd.xappdem.presenter.dependecies.DaggerRepoListComponent
import com.xappdem.pmd.xappdem.presenter.dependecies.RepoListModule
import javax.inject.Inject


/**
 * This activity contains the repolist view. It is designed to be
 * a dumb view devoid of any logic not pertaining to view manipulation.
 * For everything else dependencies are used
 */
class MainActivity : AppCompatActivity(), RepoListPresenter.RepoListView {

    @Inject
    lateinit var repoListPresenter: RepoListPresenter
    lateinit var repoListAdapter: RepoListAdapter
    lateinit var progessIndicator: ProgressBar
    lateinit var swipeContainer: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //dagger is used to inject the presenter
        //this way dummy data can be supplied by a mock for testing
        DaggerRepoListComponent.builder()
                .appComponent((application as GitApp).appComponent)
                .repoListModule(RepoListModule(this))
                .build()
                .inject(this)
        setContentView(R.layout.repo_list_view)
        repoListAdapter = RepoListAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.repo_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = repoListAdapter
        progessIndicator = findViewById(R.id.progress_bar)
        swipeContainer = findViewById(R.id.swipeContainer)
        swipeContainer.setOnRefreshListener { repoListPresenter.requestRepoList() }
        subscribeToClickEvents()
    }

    override fun onResume() {
        super.onResume()
        repoListPresenter.requestRepoList()
    }

    override fun onListDataResponse(list: List<RepoResult>) {
        if (swipeContainer.isRefreshing) swipeContainer.isRefreshing = false
        progessIndicator.visibility = View.GONE
        repoListAdapter.refreshList(list as ArrayList<RepoResult>)
    }

    private fun subscribeToClickEvents() {
        repoListAdapter.observeClicks()
                .subscribe { repo ->
                    val intent = Intent(this@MainActivity, RepoDetailActivity::class.java)
                    intent.putExtra(REPO_KEY, repo)
                    startActivity(intent)
                }
    }

    override fun onErrorResponse(errorMsg: String) {
        if (swipeContainer.isRefreshing) swipeContainer.isRefreshing = false
        progessIndicator.visibility = View.GONE
        Snackbar.make(
                findViewById<View>(android.R.id.content),
                errorMsg,
                Snackbar.LENGTH_INDEFINITE
        ).setAction("RETRY") {
            // Retry the failed request
            progessIndicator.visibility = View.VISIBLE
            repoListPresenter.requestRepoList()
        }.show()

    }

    override fun onDestroy() {
        super.onDestroy()
        repoListPresenter.onDestroy()
    }
}