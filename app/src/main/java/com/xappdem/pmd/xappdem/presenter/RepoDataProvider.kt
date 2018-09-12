package com.xappdem.pmd.xappdem.presenter

import com.xappdem.pmd.xappdem.data.RepoResult
import com.xappdem.pmd.xappdem.networking.ServiceClientHelper
import io.reactivex.Observable
import retrofit2.Call

/**
 */
class RepoDataProvider(private val repoService: ServiceClientHelper.RepoService) {

    fun provideRepoList(): Observable<List<RepoResult>> {
        return repoService.getTrendingRepos()
    }
}