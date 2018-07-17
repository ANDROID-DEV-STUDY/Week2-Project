package com.androidhuman.example.simplegithub.data.remote

import com.androidhuman.example.simplegithub.data.remote.api.GithubApi
import com.androidhuman.example.simplegithub.data.remote.model.GithubRepoRemoteModel
import com.androidhuman.example.simplegithub.data.remote.model.RepoResponseModel
import io.reactivex.Observable
import io.reactivex.Single

class GitRepoRemoteDataSource(val githubApi: GithubApi) {

    fun searchRepository(query: String) : Observable<RepoResponseModel> = githubApi.searchRepository(query)

    fun getRepository(owner:String, repoName:String): Single<GithubRepoRemoteModel> = githubApi.getRepository(owner, repoName)
}