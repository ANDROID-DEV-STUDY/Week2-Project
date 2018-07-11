package com.androidhuman.example.simplegithub.ui.main

import android.arch.lifecycle.ViewModelProviders
import com.androidhuman.example.simplegithub.data.SearchHistoryDao
import com.androidhuman.example.simplegithub.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val mainActivity: MainActivity) {

    @Provides
    fun provideMainActivity(): MainActivity = mainActivity

    @Provides
    fun provideMainViewModelFactory(searchHistoryDao: SearchHistoryDao): MainViewModelFactory
            = MainViewModelFactory(searchHistoryDao)

    @Provides @ActivityScope
    fun provideMainViewModel(mainActivity: MainActivity, mainViewModelFactory: MainViewModelFactory)
            = ViewModelProviders.of(mainActivity, mainViewModelFactory)[MainViewModel::class.java]
}