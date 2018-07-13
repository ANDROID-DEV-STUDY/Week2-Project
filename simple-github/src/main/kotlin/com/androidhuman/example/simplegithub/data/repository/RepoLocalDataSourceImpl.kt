package com.androidhuman.example.simplegithub.data.repository

import com.androidhuman.example.simplegithub.data.local.SearchHistoryDao
import com.androidhuman.example.simplegithub.data.model.GithubRepo
import io.reactivex.Flowable
import javax.inject.Inject

class RepoLocalDataSourceImpl @Inject constructor(
        private val searchHistoryDao: SearchHistoryDao
): RepoLocalDataSource {

    override fun add(repo: GithubRepo) = searchHistoryDao.add(repo)

    override fun getHistory(): Flowable<List<GithubRepo>> = searchHistoryDao.getHistory()

    override fun clearHistory() = searchHistoryDao.clearAll()
}