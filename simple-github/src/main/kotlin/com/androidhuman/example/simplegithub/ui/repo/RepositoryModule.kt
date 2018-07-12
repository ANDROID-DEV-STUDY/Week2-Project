package com.androidhuman.example.simplegithub.ui.repo

import android.arch.lifecycle.ViewModelProvider
import com.androidhuman.example.simplegithub.di.scope.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    /*
    @Binds @ActivityScope @Named("activityContext")
    abstract fun provideContext(repositoryActivity: RepositoryActivity) : Context
    */

    @Binds @ActivityScope
    abstract fun provideRepositoryViewModelFactory(repositoryViewModelFactory: RepositoryViewModelFactory): ViewModelProvider.Factory
}