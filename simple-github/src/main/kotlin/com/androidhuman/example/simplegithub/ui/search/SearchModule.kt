package com.androidhuman.example.simplegithub.ui.search

import android.arch.lifecycle.ViewModelProviders
import com.androidhuman.example.simplegithub.api.GithubApi
import com.androidhuman.example.simplegithub.data.SearchHistoryDao
import com.androidhuman.example.simplegithub.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class SearchModule(private val activity : SearchActivity) {

    @Provides
    fun provideActivity(): SearchActivity = activity

    @Provides
    fun provideSearchViewModelFactory(api: GithubApi, searchHistoryDao: SearchHistoryDao) : SearchViewModelFactory
            = SearchViewModelFactory(api, searchHistoryDao)

    @Provides @ActivityScope
    fun provideSearchViewModel(activity: SearchActivity, searchViewModelFactory: SearchViewModelFactory) : SearchViewModel
            = ViewModelProviders.of(activity, searchViewModelFactory)[SearchViewModel::class.java]
}