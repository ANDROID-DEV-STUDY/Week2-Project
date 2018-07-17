package com.androidhuman.example.simplegithub.di.module.ui

import com.androidhuman.example.simplegithub.data.local.dao.SearchHistoryDao
import com.androidhuman.example.simplegithub.ui.main.MainActivity
import com.androidhuman.example.simplegithub.ui.main.MainViewModelFactory
import com.androidhuman.example.simplegithub.ui.search.SearchAdapter
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideSearchAdapter(activity:MainActivity) : SearchAdapter
    = SearchAdapter().apply { setItemClickListener(activity) }

    @Provides
    fun provideViewModelFactory(searchHistoryDao: SearchHistoryDao) : MainViewModelFactory
    = MainViewModelFactory(searchHistoryDao)
}