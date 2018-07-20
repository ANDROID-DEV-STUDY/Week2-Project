package com.androidhuman.example.simplegithub.domain.interactor.repo

import com.androidhuman.example.simplegithub.domain.MissingUseCaseParameterException
import com.androidhuman.example.simplegithub.domain.baseUseCase.UseCaseCompletable
import com.androidhuman.example.simplegithub.domain.gateway.GitRepoGateway
import com.androidhuman.example.simplegithub.entity.GithubRepo
import io.reactivex.Completable

class SaveRepo(private val gateway: GitRepoGateway): UseCaseCompletable<GithubRepo>() {

    override fun buildUseCaseCompletable(params: GithubRepo?): Completable {
        return if(params!=null) {
            gateway.saveRepo(params)
        } else {
            throw MissingUseCaseParameterException(javaClass)
        }
    }
}