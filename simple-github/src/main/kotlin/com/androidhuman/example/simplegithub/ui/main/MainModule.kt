package com.androidhuman.example.simplegithub.ui.main

import android.arch.lifecycle.ViewModelProvider
import com.androidhuman.example.simplegithub.di.scope.ActivityScope
import com.androidhuman.example.simplegithub.ui.search.SearchAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [AdapterModule::class])
abstract class MainModule {

    @Binds @ActivityScope
    abstract fun provideMainViewModelFactory(mainViewModelFactory: MainViewModelFactory): ViewModelProvider.Factory
}

@Module
class AdapterModule {

    @Provides
    @ActivityScope
    fun provideSearchAdapter(): SearchAdapter = SearchAdapter()
}