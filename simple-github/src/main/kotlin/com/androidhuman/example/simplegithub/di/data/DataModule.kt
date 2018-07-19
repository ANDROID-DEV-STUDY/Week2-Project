package com.androidhuman.example.simplegithub.di.data

import com.androidhuman.example.simplegithub.data.local.AuthLocalDataSource
import com.androidhuman.example.simplegithub.data.local.GitRepoLocalDataSource
import com.androidhuman.example.simplegithub.data.remote.AuthRemoteDataSource
import com.androidhuman.example.simplegithub.data.remote.GitRepoRemoteDataSource
import com.androidhuman.example.simplegithub.data.repository.AuthRepository
import com.androidhuman.example.simplegithub.data.repository.GitRepoRepository
import com.androidhuman.example.simplegithub.di.data.ApiModule
import com.androidhuman.example.simplegithub.di.data.DataSourceModule
import com.androidhuman.example.simplegithub.di.data.LocalDataModule
import com.androidhuman.example.simplegithub.di.data.NetworkModule
import com.androidhuman.example.simplegithub.domain.gateway.GitAuthGateway
import com.androidhuman.example.simplegithub.domain.gateway.GitRepoGateway
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [
    ApiModule::class,
    LocalDataModule::class,
    NetworkModule::class,
    DataSourceModule::class])
class DataModule {

    @Provides
    @Singleton
    fun provideAuthRepository(authRemoteDataSource: AuthRemoteDataSource,
                              authLocalDataSource: AuthLocalDataSource): GitAuthGateway
            = AuthRepository(authLocalDataSource, authRemoteDataSource)

    @Provides
    @Singleton
    fun provideGitRepoRepository(gitRepoRemoteDataSource: GitRepoRemoteDataSource,
                                 gitRepoLocalDataSource: GitRepoLocalDataSource): GitRepoGateway
            = GitRepoRepository(gitRepoLocalDataSource, gitRepoRemoteDataSource)
}