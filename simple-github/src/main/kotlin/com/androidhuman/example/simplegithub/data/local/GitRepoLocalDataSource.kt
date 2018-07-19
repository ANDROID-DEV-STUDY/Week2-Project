package com.androidhuman.example.simplegithub.data.local

import com.androidhuman.example.simplegithub.data.local.dao.SearchHistoryDao
import com.androidhuman.example.simplegithub.data.local.model.GithubRepoLocalModel
import io.reactivex.Completable
import io.reactivex.Flowable

class GitRepoLocalDataSource(private val searchHistoryDao: SearchHistoryDao) {

    fun add(repo: GithubRepoLocalModel) = searchHistoryDao.add(repo)

    fun getHistory(): Flowable<List<GithubRepoLocalModel>> = searchHistoryDao.getHistory()

    fun clearAll() = searchHistoryDao.clearAll()
}