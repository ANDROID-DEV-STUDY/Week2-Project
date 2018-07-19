package com.androidhuman.example.simplegithub.domain.interactor.repo

import com.androidhuman.example.simplegithub.domain.MissingUseCaseParameterException
import com.androidhuman.example.simplegithub.domain.baseUseCase.UseCaseCompletable
import com.androidhuman.example.simplegithub.domain.gateway.GitRepoGateway
import com.androidhuman.example.simplegithub.entity.GithubRepo
import io.reactivex.Completable

class SaveRepo(private val gateway: GitRepoGateway): UseCaseCompletable<SaveRepo.Params>() {

    override fun buildUseCaseCompletable(params: Params?): Completable {
        params?.let {
            gateway.saveRepo(it.repo)
        }.let {
            throw MissingUseCaseParameterException(javaClass)
        }
    }

    data class Params(val repo: GithubRepo)
}