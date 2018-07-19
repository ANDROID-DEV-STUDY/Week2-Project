package com.androidhuman.example.simplegithub.domain.interactor.auth

import com.androidhuman.example.simplegithub.domain.MissingUseCaseParameterException
import com.androidhuman.example.simplegithub.domain.baseUseCase.UseCaseCompletable
import com.androidhuman.example.simplegithub.domain.baseUseCase.UseCaseMaybe
import com.androidhuman.example.simplegithub.domain.baseUseCase.UseCaseSingle
import com.androidhuman.example.simplegithub.domain.gateway.GitAuthGateway
import com.androidhuman.example.simplegithub.entity.GithubAccessToken
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class GetAccessTokenByCode(private val gateway: GitAuthGateway) : UseCaseSingle<GithubAccessToken, GetAccessTokenByCode.Params>() {

    override fun buildUseCaseSingle(params: Params?): Single<GithubAccessToken> {
        params?.let { gateway.getAccessTokenByCode(it.code) }
                .let { throw MissingUseCaseParameterException(javaClass) }
    }

    data class Params(val code: String)
}