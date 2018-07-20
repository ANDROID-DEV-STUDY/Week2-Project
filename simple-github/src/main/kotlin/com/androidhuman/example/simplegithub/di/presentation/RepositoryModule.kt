package com.androidhuman.example.simplegithub.di.presentation

import com.androidhuman.example.simplegithub.data.remote.api.GithubApi
import com.androidhuman.example.simplegithub.domain.Schedulers
import com.androidhuman.example.simplegithub.domain.interactor.repo.GetRepo
import com.androidhuman.example.simplegithub.presentation.repo.RepositoryViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class RepositoryModule {

    @Provides
    fun provideViewModelFactory(getRepo: GetRepo,
                                @Named("appScheduler")schedulers: Schedulers) : RepositoryViewModelFactory
    = RepositoryViewModelFactory(getRepo, schedulers)
}