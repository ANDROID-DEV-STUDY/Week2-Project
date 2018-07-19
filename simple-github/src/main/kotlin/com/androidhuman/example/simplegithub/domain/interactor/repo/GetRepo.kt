package com.androidhuman.example.simplegithub.domain.interactor.repo

import com.androidhuman.example.simplegithub.domain.MissingUseCaseParameterException
import com.androidhuman.example.simplegithub.domain.baseUseCase.UseCaseSingle
import com.androidhuman.example.simplegithub.domain.gateway.GitRepoGateway
import com.androidhuman.example.simplegithub.entity.GithubRepo
import io.reactivex.Single

class GetRepo(private val gateway: GitRepoGateway) : UseCaseSingle<GithubRepo, Pair<String, String>>() {

    override fun buildUseCaseSingle(params: Pair<String, String>?): Single<GithubRepo> {
        params?.let {
            val (owner, repoName) = it
            gateway.getRepo(owner, repoName)
        }.let {
            throw MissingUseCaseParameterException(javaClass)
        }
    }
}