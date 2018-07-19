package com.androidhuman.example.simplegithub.di.presentation

import com.androidhuman.example.simplegithub.data.remote.api.GithubApi
import com.androidhuman.example.simplegithub.data.local.dao.SearchHistoryDao
import com.androidhuman.example.simplegithub.ui.search.SearchActivity
import com.androidhuman.example.simplegithub.ui.search.SearchAdapter
import com.androidhuman.example.simplegithub.ui.search.SearchViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    fun provideAdapter(activity: SearchActivity) : SearchAdapter
    = SearchAdapter().apply { setItemClickListener(activity) }

    @Provides
    fun provideViewModelFactory(githubApi: GithubApi, searchHistoryDao: SearchHistoryDao) : SearchViewModelFactory
    = SearchViewModelFactory(githubApi, searchHistoryDao)
}