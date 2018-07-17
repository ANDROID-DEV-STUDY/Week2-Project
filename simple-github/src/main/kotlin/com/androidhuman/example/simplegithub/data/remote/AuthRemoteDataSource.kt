package com.androidhuman.example.simplegithub.data.remote

import com.androidhuman.example.simplegithub.data.remote.api.AuthApi
import com.androidhuman.example.simplegithub.data.remote.model.GithubTokenRemoteModel
import io.reactivex.Observable

class AuthRemoteDataSource(val authApi: AuthApi) {

    fun getAccessToken(clientId: String, clientSecret: String, code: String) : Observable<GithubTokenRemoteModel>
            = authApi.getAccessToken(clientId, clientSecret, code)
}