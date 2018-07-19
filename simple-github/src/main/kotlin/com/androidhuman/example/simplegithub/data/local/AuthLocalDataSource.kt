package com.androidhuman.example.simplegithub.data.local

import com.androidhuman.example.simplegithub.data.local.sharedpreference.AuthTokenPreference
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class AuthLocalDataSource(private val authTokenPreference: AuthTokenPreference) {

    fun updateToken(token: String) = authTokenPreference.updateToken(token)

    fun getAuthToken(): Maybe<String> = Maybe.fromCallable { authTokenPreference.token }
}