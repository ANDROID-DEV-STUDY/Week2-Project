package com.androidhuman.example.simplegithub.domain.interactor.auth

import android.util.Log
import com.androidhuman.example.simplegithub.domain.MissingUseCaseParameterException
import com.androidhuman.example.simplegithub.domain.baseUseCase.UseCaseSingle
import com.androidhuman.example.simplegithub.domain.gateway.GitAuthGateway
import com.androidhuman.example.simplegithub.entity.GithubAccessToken
import io.reactivex.Single

class GetAccessTokenByCode(private val gateway: GitAuthGateway) : UseCaseSingle<GithubAccessToken, String>() {

    override fun buildUseCaseSingle(params: String?): Single<GithubAccessToken> {
        return if(params != null) {
            gateway.getAccessTokenByCode(params)
        } else {
            throw MissingUseCaseParameterException(javaClass)
        }

    }
}