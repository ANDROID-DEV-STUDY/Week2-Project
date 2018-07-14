package com.androidhuman.example.simplegithub.data.repository

import com.androidhuman.example.simplegithub.data.model.GithubRepo
import io.reactivex.Completable
import io.reactivex.Flowable

interface RepoLocalDataSource {

    fun add(repo: GithubRepo): Completable

    fun getHistory(): Flowable<List<GithubRepo>>

    fun clearHistory(): Completable
}