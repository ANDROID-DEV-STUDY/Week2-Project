package com.androidhuman.example.simplegithub.domain.interactor.auth

import com.androidhuman.example.simplegithub.domain.MissingUseCaseParameterException
import com.androidhuman.example.simplegithub.domain.baseUseCase.UseCaseSingle
import com.androidhuman.example.simplegithub.domain.gateway.GitAuthGateway
import com.androidhuman.example.simplegithub.entity.GithubAccessToken
import io.reactivex.Single

class GetAccessTokenByCode(private val gateway: GitAuthGateway) : UseCaseSingle<GithubAccessToken, String>() {

    override fun buildUseCaseSingle(params: String?): Single<GithubAccessToken> {
        params?.let { gateway.getAccessTokenByCode(it) }
                .let { throw MissingUseCaseParameterException(javaClass) }

    }
}