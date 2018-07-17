package com.androidhuman.example.simplegithub.data.gateway

import com.androidhuman.example.simplegithub.domain.gateway.GitRepoGateway
import com.androidhuman.example.simplegithub.entity.GithubRepo
import io.reactivex.Flowable
import io.reactivex.Single

class GitRepoGatewayImpl: GitRepoGateway {
    override fun getHistoryRepos(): Flowable<List<GithubRepo>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRepo(owner: String, repoName: String): Single<GithubRepo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchReposByName(name: String): Flowable<List<GithubRepo>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}