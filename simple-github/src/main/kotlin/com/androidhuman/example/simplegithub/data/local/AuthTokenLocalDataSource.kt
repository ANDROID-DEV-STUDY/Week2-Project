package com.androidhuman.example.simplegithub.data.local

import com.androidhuman.example.simplegithub.data.local.sharedpreference.AuthTokenPreference
import io.reactivex.Single

class AuthTokenLocalDataSource(private val authTokenPreference: AuthTokenPreference) {

    fun updateToken(token: String) = authTokenPreference.updateToken(token)

    fun getAuthToken(): Single<String> = Single.just(authTokenPreference.token)
}