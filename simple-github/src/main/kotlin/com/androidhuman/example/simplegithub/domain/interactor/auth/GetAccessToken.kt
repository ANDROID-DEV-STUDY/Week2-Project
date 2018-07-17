package com.androidhuman.example.simplegithub.domain.interactor.auth

import com.androidhuman.example.simplegithub.domain.baseUseCase.UseCaseMaybe
import com.androidhuman.example.simplegithub.domain.gateway.GitAuthGateway
import com.androidhuman.example.simplegithub.entity.GithubAccessToken
import io.reactivex.Maybe
import io.reactivex.Single

class GetAccessToken(private val gateway: GitAuthGateway) : UseCaseMaybe<GithubAccessToken, Void>() {

    override fun buildUseCaseMaybe(params: Void?): Maybe<GithubAccessToken> = gateway.getAccessToken()
}