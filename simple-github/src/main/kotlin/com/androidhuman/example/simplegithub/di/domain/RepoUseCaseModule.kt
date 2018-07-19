package com.androidhuman.example.simplegithub.di.domain

import com.androidhuman.example.simplegithub.domain.gateway.GitRepoGateway
import com.androidhuman.example.simplegithub.domain.interactor.repo.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoUseCaseModule {

    @Provides
    @Singleton
    fun provideClearHistoryReposUseCase(gateway: GitRepoGateway): ClearHistoryRepos = ClearHistoryRepos(gateway)

    @Provides
    @Singleton
    fun provideGetHistoryReposUseCase(gateway: GitRepoGateway): GetHistoryRepos = GetHistoryRepos(gateway)

    @Provides
    @Singleton
    fun provideGetRepoUseCase(gateway: GitRepoGateway): GetRepo = GetRepo(gateway)

    @Provides
    @Singleton
    fun provideSaveRepoUseCase(gateway: GitRepoGateway): SaveRepo = SaveRepo(gateway)

    @Provides
    @Singleton
    fun provideSearchReposByNameUseCase(gateway: GitRepoGateway): SearchReposByName = SearchReposByName(gateway)
}