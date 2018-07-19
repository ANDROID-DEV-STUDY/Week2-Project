package com.androidhuman.example.simplegithub.di.data

import com.androidhuman.example.simplegithub.data.local.AuthLocalDataSource
import com.androidhuman.example.simplegithub.data.local.GitRepoLocalDataSource
import com.androidhuman.example.simplegithub.data.local.dao.SearchHistoryDao
import com.androidhuman.example.simplegithub.data.local.sharedpreference.AuthTokenPreference
import com.androidhuman.example.simplegithub.data.remote.AuthRemoteDataSource
import com.androidhuman.example.simplegithub.data.remote.GitRepoRemoteDataSource
import com.androidhuman.example.simplegithub.data.remote.api.AuthApi
import com.androidhuman.example.simplegithub.data.remote.api.GithubApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {
    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(api: AuthApi): AuthRemoteDataSource
            = AuthRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideGitRepoRemoteDataSourece(api: GithubApi): GitRepoRemoteDataSource
            = GitRepoRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideAuthLocalDataSource(authTokenPreference: AuthTokenPreference): AuthLocalDataSource
            = AuthLocalDataSource(authTokenPreference)

    @Provides
    @Singleton
    fun provideGitRepoLocalDataSource(dao: SearchHistoryDao): GitRepoLocalDataSource
            = GitRepoLocalDataSource(dao)
}