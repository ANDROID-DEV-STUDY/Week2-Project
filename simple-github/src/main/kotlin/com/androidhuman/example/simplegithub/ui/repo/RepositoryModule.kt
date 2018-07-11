package com.androidhuman.example.simplegithub.ui.repo

import android.arch.lifecycle.ViewModelProviders
import com.androidhuman.example.simplegithub.api.GithubApi
import com.androidhuman.example.simplegithub.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule(private val repositoryActivity: RepositoryActivity) {

    @Provides
    fun provideRepositoryActivity(): RepositoryActivity = repositoryActivity

    @Provides
    fun provideRepositoryViewModelFactory(api : GithubApi): RepositoryViewModelFactory
            = RepositoryViewModelFactory(api)

    @Provides @ActivityScope
    fun provideRepositoryViewModel(repositoryActivity: RepositoryActivity, repositoryViewModelFactory: RepositoryViewModelFactory)
            = ViewModelProviders.of(repositoryActivity, repositoryViewModelFactory)[RepositoryViewModel::class.java]
}