package com.androidhuman.example.simplegithub.ui.main

import android.arch.lifecycle.ViewModelProvider
import com.androidhuman.example.simplegithub.di.scope.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {

    @Binds @ActivityScope
    abstract fun provideMainViewModelFactory(mainViewModelFactory: MainViewModelFactory): ViewModelProvider.Factory
}