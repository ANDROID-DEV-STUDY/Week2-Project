package com.androidhuman.example.simplegithub.domain.interactor.auth

import com.androidhuman.example.simplegithub.domain.baseUseCase.UseCaseSingle
import com.androidhuman.example.simplegithub.domain.gateway.GitAuthGateway
import io.reactivex.Single

class GetAccessToken(private val gateway: GitAuthGateway) : UseCaseSingle<String, Void>() {

    override fun buildUseCaseSingle(params: Void?): Single<String> = gateway.getAccessToken()
}