package com.androidhuman.example.simplegithub.ui.search

import android.arch.lifecycle.ViewModelProvider
import com.androidhuman.example.simplegithub.di.scope.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class SearchModule {

    @Binds @ActivityScope
    abstract fun provideSearchViewModelFactory(searchViewModelFactory: SearchViewModelFactory) : ViewModelProvider.Factory
}