package com.androidhuman.example.simplegithub.data.repository

import com.androidhuman.example.simplegithub.data.model.GithubRepo
import com.androidhuman.example.simplegithub.data.model.RepoSearchResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
        private val repoLocalDataSource: RepoLocalDataSource,
        private val repoRemoteDataSource: RepoRemoteDataSource
): RepoRepository {

    override fun add(repo: GithubRepo) = repoLocalDataSource.add(repo)

    override fun getHistory(): Flowable<List<GithubRepo>> = repoLocalDataSource.getHistory()

    override fun clearHistory() = repoLocalDataSource.clearHistory()

    override fun searchRepository(query: String): Observable<RepoSearchResponse> = repoRemoteDataSource.searchRepository(query)

    override fun getRepository(ownerLogin: String, repoName: String): Observable<GithubRepo>
            = repoRemoteDataSource.getRepository(ownerLogin, repoName)
}