package com.androidhuman.example.simplegithub.presentation.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.androidhuman.example.simplegithub.domain.Schedulers
import com.androidhuman.example.simplegithub.domain.interactor.repo.SaveRepo
import com.androidhuman.example.simplegithub.domain.interactor.repo.SearchReposByName

class SearchViewModelFactory(
        val searchReposByName: SearchReposByName,
        val saveRepo: SaveRepo,
        val appSchedulers: Schedulers,
        val ioSchedulers: Schedulers)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return SearchViewModel(searchReposByName, saveRepo, appSchedulers, ioSchedulers) as T
    }
}
