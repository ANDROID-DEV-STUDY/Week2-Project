package com.androidhuman.example.simplegithub.domain.gateway

import com.androidhuman.example.simplegithub.entity.GithubRepo
import io.reactivex.Flowable
import io.reactivex.Single

interface GitRepoGateway {
    fun getHistoryRepos() : Flowable<List<GithubRepo>>
    fun getRepo(owner: String, repoName: String) : Single<GithubRepo>
    fun searchReposByName(name: String) : Flowable<List<GithubRepo>>
}