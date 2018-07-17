package com.androidhuman.example.simplegithub.domain.gateway

import com.androidhuman.example.simplegithub.entity.GithubAccessToken
import io.reactivex.Maybe
import io.reactivex.Single

interface GitAuthGateway {
    fun getAccessToken() : Maybe<GithubAccessToken>
    fun saveAccessTokenByCode(code: String) : Single<Boolean>
}