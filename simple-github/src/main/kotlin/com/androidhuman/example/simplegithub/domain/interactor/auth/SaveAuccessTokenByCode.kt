package com.androidhuman.example.simplegithub.domain.interactor.auth

import com.androidhuman.example.simplegithub.domain.MissingUseCaseParameterException
import com.androidhuman.example.simplegithub.domain.baseUseCase.UseCaseSingle
import com.androidhuman.example.simplegithub.domain.gateway.GitAuthGateway
import io.reactivex.Single

class SaveAuccessTokenByCode(private val gateway: GitAuthGateway) : UseCaseSingle<Boolean, SaveAuccessTokenByCode.Params>() {

    override fun buildUseCaseSingle(params: Params?): Single<Boolean> {
        params?.let { gateway.saveAccessTokenByCode(it.code) }
                .let { throw MissingUseCaseParameterException(javaClass) }
    }

    data class Params(val code: String)
}