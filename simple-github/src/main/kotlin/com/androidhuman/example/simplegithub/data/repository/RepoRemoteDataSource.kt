package com.androidhuman.example.simplegithub.data.repository

import com.androidhuman.example.simplegithub.data.model.GithubRepo
import com.androidhuman.example.simplegithub.data.model.RepoSearchResponse
import io.reactivex.Observable

interface RepoRemoteDataSource {

    fun searchRepository(query: String): Observable<RepoSearchResponse>

    fun getRepository(ownerLogin: String, repoName: String): Observable<GithubRepo>
}