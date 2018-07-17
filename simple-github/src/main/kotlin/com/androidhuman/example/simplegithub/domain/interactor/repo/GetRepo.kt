package com.androidhuman.example.simplegithub.domain.interactor.repo

import com.androidhuman.example.simplegithub.domain.MissingUseCaseParameterException
import com.androidhuman.example.simplegithub.domain.baseUseCase.UseCaseSingle
import com.androidhuman.example.simplegithub.domain.gateway.GitRepoGateway
import com.androidhuman.example.simplegithub.entity.GithubRepo
import io.reactivex.Single

class GetRepo(private val gateway: GitRepoGateway) : UseCaseSingle<GithubRepo, GetRepo.Params>() {

    override fun buildUseCaseSingle(params: Params?): Single<GithubRepo> {
        params?.let {
            gateway.getRepo(it.owner, it.repoName)
        }.let {
            throw MissingUseCaseParameterException(javaClass)
        }
    }

    data class Params(val owner: String, val repoName: String)
}