package com.androidhuman.example.simplegithub.di.module

import com.androidhuman.example.simplegithub.data.repository.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds @Singleton
    abstract fun provideRepoLocalDataSource(repoLocalDataSourceImpl: RepoLocalDataSourceImpl): RepoLocalDataSource

    @Binds @Singleton
    abstract fun provideRepoRemoteDataSource(repoRemoteDataSourceImpl: RepoRemoteDataSourceImpl): RepoRemoteDataSource

    @Binds @Singleton
    abstract fun provideRepoRepository(repoRepositoryImpl: RepoRepositoryImpl): RepoRepository
}