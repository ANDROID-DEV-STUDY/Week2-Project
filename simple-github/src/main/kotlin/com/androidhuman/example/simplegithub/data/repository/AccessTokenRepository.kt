package com.androidhuman.example.simplegithub.data.repository

import com.androidhuman.example.simplegithub.data.local.AuthTokenLocalDataSource
import com.androidhuman.example.simplegithub.data.remote.AuthRemoteDataSource

class AccessTokenRepository(
        private val tokenLocalDataSource: AuthTokenLocalDataSource,
        private val tokenRemoteDataSource: AuthRemoteDataSource
) {

}