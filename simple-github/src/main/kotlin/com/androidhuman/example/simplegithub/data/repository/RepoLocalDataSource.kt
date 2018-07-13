package com.androidhuman.example.simplegithub.data.repository

import com.androidhuman.example.simplegithub.data.model.GithubRepo
import io.reactivex.Flowable

interface RepoLocalDataSource {

    fun add(repo: GithubRepo)

    fun getHistory(): Flowable<List<GithubRepo>>

    fun clearHistory()
}