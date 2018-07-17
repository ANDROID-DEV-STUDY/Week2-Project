package com.androidhuman.example.simplegithub.data.gateway

import com.androidhuman.example.simplegithub.domain.gateway.GitAuthGateway
import com.androidhuman.example.simplegithub.entity.GithubAccessToken
import io.reactivex.Maybe
import io.reactivex.Single

class GitAuthGatewayImpl : GitAuthGateway {
    override fun getAccessToken(): Maybe<GithubAccessToken> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveAccessTokenByCode(code: String): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}