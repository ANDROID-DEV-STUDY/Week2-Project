package com.androidhuman.example.simplegithub.ui.search

import android.arch.lifecycle.MutableLiveData
import com.androidhuman.example.simplegithub.data.local.SearchHistoryDao
import com.androidhuman.example.simplegithub.data.model.GithubRepo
import com.androidhuman.example.simplegithub.data.remote.GithubApi
import com.androidhuman.example.simplegithub.data.repository.RepoRepository
import com.androidhuman.example.simplegithub.ui.base.RxBaseViewModel
import com.androidhuman.example.simplegithub.util.State
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchViewModel constructor(
        private val repository: RepoRepository,
        private val api: GithubApi,
        private val searchHistoryDao: SearchHistoryDao
) : RxBaseViewModel() {

    val state: MutableLiveData<State<List<GithubRepo>>> = MutableLiveData()

    val lastSearchKeyword: MutableLiveData<String> = MutableLiveData()

    fun searchRepository(query: String)
            = repository.searchRepository(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { state.value = State.Loading() }
            .doOnNext { lastSearchKeyword.value = query }
            .flatMap {
                if (0 == it.totalCount) {
                    Observable.error(IllegalStateException("No search result"))
                } else {
                    Observable.just(it.items)
                }
            }
            .doOnTerminate { state.value = State.Init() }
            .subscribe({
                state.value = State.Success(it)
            }, {
                state.value = State.Error(it.message ?: "Unexpected error")
            })
            .let { mDisposable.add(it) }

    fun addToSearchHistory(repo: GithubRepo)
            = repository.add(repo)
            .subscribeOn(Schedulers.io())
            .subscribe()
            .let { mDisposable.add(it) }
}
