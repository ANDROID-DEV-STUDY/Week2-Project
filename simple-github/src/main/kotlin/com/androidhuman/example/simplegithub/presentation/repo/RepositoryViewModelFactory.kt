package com.androidhuman.example.simplegithub.presentation.repo

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.androidhuman.example.simplegithub.data.remote.api.GithubApi
import com.androidhuman.example.simplegithub.domain.Schedulers
import com.androidhuman.example.simplegithub.domain.interactor.repo.GetRepo

class RepositoryViewModelFactory(val getRepo: GetRepo,
                                 val schedulers: Schedulers) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return RepositoryViewModel(getRepo, schedulers) as T
    }
}
