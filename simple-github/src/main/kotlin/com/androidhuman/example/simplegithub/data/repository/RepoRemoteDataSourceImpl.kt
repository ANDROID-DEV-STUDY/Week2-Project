package com.androidhuman.example.simplegithub.data.repository

import com.androidhuman.example.simplegithub.data.model.GithubRepo
import com.androidhuman.example.simplegithub.data.model.RepoSearchResponse
import com.androidhuman.example.simplegithub.data.remote.GithubApi
import io.reactivex.Observable
import javax.inject.Inject

class RepoRemoteDataSourceImpl @Inject constructor(
        private val githubApi: GithubApi
) : RepoRemoteDataSource {

    override fun searchRepository(query: String): Observable<RepoSearchResponse>
            = githubApi.searchRepository(query)

    override fun getRepository(ownerLogin: String, repoName: String): Observable<GithubRepo>
            = githubApi.getRepository(ownerLogin, repoName)
}