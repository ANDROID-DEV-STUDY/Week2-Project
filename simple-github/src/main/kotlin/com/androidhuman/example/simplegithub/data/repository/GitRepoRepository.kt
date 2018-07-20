package com.androidhuman.example.simplegithub.data.repository

import com.androidhuman.example.simplegithub.data.local.GitRepoLocalDataSource
import com.androidhuman.example.simplegithub.data.mapper.GitRepoMapper
import com.androidhuman.example.simplegithub.data.remote.GitRepoRemoteDataSource
import com.androidhuman.example.simplegithub.domain.gateway.GitRepoGateway
import com.androidhuman.example.simplegithub.entity.GithubRepo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

class GitRepoRepository(
        private val gitRepoLocalDataSource: GitRepoLocalDataSource,
        private val gitRepoRemoteDataSource: GitRepoRemoteDataSource) : GitRepoGateway {

    private val gitRepoMapper: GitRepoMapper = GitRepoMapper()

    override fun getHistoryRepos(): Flowable<List<GithubRepo>> {
        return gitRepoLocalDataSource.getHistory()
                .flatMap {
                    if(it.isEmpty()) {
                        Flowable.error(IllegalStateException("No recent repositories."))
                    } else {
                        Flowable.just( it.map { gitRepoMapper.toEntity(it)} )
                    }
                }
    }

    override fun saveRepo(githubRepo: GithubRepo): Completable {
        return Completable.fromCallable {
            gitRepoLocalDataSource.add(gitRepoMapper.toLocal(githubRepo))
        }
    }

    override fun clearHistoryRepos(): Completable {
        return Completable.fromCallable {
            gitRepoLocalDataSource.clearAll()
        }
    }

    override fun getRepo(owner: String, repoName: String): Single<GithubRepo> {
        return gitRepoRemoteDataSource.getRepository(owner, repoName)
                .map { gitRepoMapper.toEntity(it) }
    }

    override fun searchReposByName(name: String): Observable<List<GithubRepo>> {
        return gitRepoRemoteDataSource.searchRepository(name)
                .flatMap {
                    if (0 == it.totalCount) {
                        Observable.error(IllegalStateException("No search result"))
                    } else {
                        Observable.just(it.items.map { gitRepoMapper.toEntity(it) })
                    }
                }
    }
}