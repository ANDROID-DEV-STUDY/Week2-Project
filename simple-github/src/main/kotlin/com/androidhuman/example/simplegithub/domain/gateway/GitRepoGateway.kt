package com.androidhuman.example.simplegithub.domain.gateway

import com.androidhuman.example.simplegithub.entity.GithubRepo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface GitRepoGateway {
    fun getHistoryRepos() : Flowable<List<GithubRepo>>
    fun clearHistoryRepos() : Completable
    fun saveRepo(githubRepo: GithubRepo) : Completable
    fun getRepo(owner: String, repoName: String) : Single<GithubRepo>
    fun searchReposByName(name: String) : Observable<List<GithubRepo>>
}