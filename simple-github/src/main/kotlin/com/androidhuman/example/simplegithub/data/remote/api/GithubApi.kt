package com.androidhuman.example.simplegithub.data.remote.api

import com.androidhuman.example.simplegithub.data.remote.model.GithubRepoRemoteModel
import com.androidhuman.example.simplegithub.data.remote.model.RepoResponseModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    fun searchRepository(@Query("q") query: String): Observable<RepoResponseModel>

    @GET("repos/{owner}/{name}")
    fun getRepository(
            @Path("owner") ownerLogin: String,
            @Path("name") repoName: String): Single<GithubRepoRemoteModel>
}
