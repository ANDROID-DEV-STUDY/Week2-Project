package com.androidhuman.example.simplegithub.di.presentation

import com.androidhuman.example.simplegithub.data.local.dao.SearchHistoryDao
import com.androidhuman.example.simplegithub.domain.Schedulers
import com.androidhuman.example.simplegithub.domain.interactor.repo.ClearHistoryRepos
import com.androidhuman.example.simplegithub.domain.interactor.repo.GetHistoryRepos
import com.androidhuman.example.simplegithub.presentation.main.MainActivity
import com.androidhuman.example.simplegithub.presentation.main.MainViewModelFactory
import com.androidhuman.example.simplegithub.presentation.search.SearchAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class MainModule {

    @Provides
    fun provideSearchAdapter(activity:MainActivity) : SearchAdapter
    = SearchAdapter().apply { setItemClickListener(activity) }

    @Provides
    fun provideViewModelFactory(getHistoryRepos: GetHistoryRepos,
                                clearHistoryRepos: ClearHistoryRepos,
                                @Named("appScheduler")appSchedulers: Schedulers,
                                @Named("ioScheduler")ioSchedulers: Schedulers) : MainViewModelFactory
    = MainViewModelFactory(getHistoryRepos, clearHistoryRepos, appSchedulers, ioSchedulers)
}