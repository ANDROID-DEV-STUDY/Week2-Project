package com.androidhuman.example.simplegithub.domain.interactor.repo

import com.androidhuman.example.simplegithub.domain.MissingUseCaseParameterException
import com.androidhuman.example.simplegithub.domain.baseUseCase.UseCaseObservable
import com.androidhuman.example.simplegithub.domain.gateway.GitRepoGateway
import com.androidhuman.example.simplegithub.entity.GithubRepo
import io.reactivex.Observable

class SearchReposByName(private val gateway: GitRepoGateway) : UseCaseObservable<List<GithubRepo>, String>() {

    override fun buildUseCaseObservable(params: String?): Observable<List<GithubRepo>> {
        return if(params != null) {
            gateway.searchReposByName(params)
        } else {
            throw MissingUseCaseParameterException(javaClass)
        }
    }
}