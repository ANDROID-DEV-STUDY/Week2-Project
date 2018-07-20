package com.androidhuman.example.simplegithub.presentation.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.androidhuman.example.simplegithub.data.local.dao.SearchHistoryDao
import com.androidhuman.example.simplegithub.domain.Schedulers
import com.androidhuman.example.simplegithub.domain.interactor.repo.ClearHistoryRepos
import com.androidhuman.example.simplegithub.domain.interactor.repo.GetHistoryRepos

class MainViewModelFactory(
        val getHistoryRepos: GetHistoryRepos,
        val clearHistoryRepos: ClearHistoryRepos,
        val appSchedulers: Schedulers,
        val ioSchedulers: Schedulers)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MainViewModel(getHistoryRepos, clearHistoryRepos, appSchedulers, ioSchedulers) as T
    }
}
