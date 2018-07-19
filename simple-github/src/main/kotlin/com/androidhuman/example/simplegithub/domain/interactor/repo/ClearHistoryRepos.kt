package com.androidhuman.example.simplegithub.domain.interactor.repo

import com.androidhuman.example.simplegithub.domain.MissingUseCaseParameterException
import com.androidhuman.example.simplegithub.domain.baseUseCase.UseCaseCompletable
import com.androidhuman.example.simplegithub.domain.gateway.GitRepoGateway
import com.androidhuman.example.simplegithub.entity.GithubRepo
import io.reactivex.Completable

class ClearHistoryRepos(val gateway: GitRepoGateway): UseCaseCompletable<Void>() {

    override fun buildUseCaseCompletable(params: Void?): Completable {
        return gateway.clearHistoryRepos()
    }

}