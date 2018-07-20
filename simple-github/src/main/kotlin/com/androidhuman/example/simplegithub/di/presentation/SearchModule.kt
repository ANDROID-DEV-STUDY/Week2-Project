package com.androidhuman.example.simplegithub.di.presentation

import com.androidhuman.example.simplegithub.domain.Schedulers
import com.androidhuman.example.simplegithub.domain.interactor.repo.SaveRepo
import com.androidhuman.example.simplegithub.domain.interactor.repo.SearchReposByName
import com.androidhuman.example.simplegithub.presentation.search.SearchActivity
import com.androidhuman.example.simplegithub.presentation.search.SearchAdapter
import com.androidhuman.example.simplegithub.presentation.search.SearchViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SearchModule {

    @Provides
    fun provideAdapter(activity: SearchActivity) : SearchAdapter
    = SearchAdapter().apply { setItemClickListener(activity) }

    @Provides
    fun provideViewModelFactory(searchReposByName: SearchReposByName,
                                saveRepo: SaveRepo,
                                @Named("appScheduler")appSchedulers: Schedulers,
                                @Named("ioScheduler")ioSchedulers: Schedulers) : SearchViewModelFactory
    = SearchViewModelFactory(searchReposByName, saveRepo, appSchedulers, ioSchedulers)
}