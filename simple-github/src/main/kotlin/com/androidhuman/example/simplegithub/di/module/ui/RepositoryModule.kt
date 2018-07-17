package com.androidhuman.example.simplegithub.di.module.ui

import com.androidhuman.example.simplegithub.data.remote.api.GithubApi
import com.androidhuman.example.simplegithub.ui.repo.RepositoryViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideViewModelFactory(githubApi: GithubApi) : RepositoryViewModelFactory
    = RepositoryViewModelFactory(githubApi)
}