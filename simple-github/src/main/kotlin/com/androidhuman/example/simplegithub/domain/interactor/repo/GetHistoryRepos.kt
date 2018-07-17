package com.androidhuman.example.simplegithub.domain.interactor.repo

import com.androidhuman.example.simplegithub.domain.baseUseCase.UseCaseFlowable
import com.androidhuman.example.simplegithub.domain.gateway.GitRepoGateway
import com.androidhuman.example.simplegithub.entity.GithubRepo
import io.reactivex.Flowable

class GetHistoryRepos(private val gateway: GitRepoGateway): UseCaseFlowable<List<GithubRepo>, Void>() {
    override fun buildUseCaseFlowable(params: Void?): Flowable<List<GithubRepo>> {
        return gateway.getHistoryRepos()
    }
}