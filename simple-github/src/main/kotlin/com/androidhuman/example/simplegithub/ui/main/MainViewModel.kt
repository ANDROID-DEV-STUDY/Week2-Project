package com.androidhuman.example.simplegithub.ui.main

import android.arch.lifecycle.MutableLiveData
import com.androidhuman.example.simplegithub.data.model.GithubRepo
import com.androidhuman.example.simplegithub.data.repository.RepoRepository
import com.androidhuman.example.simplegithub.ui.base.RxBaseViewModel
import com.androidhuman.example.simplegithub.util.State
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel constructor(
        private val repository : RepoRepository
) : RxBaseViewModel() {

    val state: MutableLiveData<State<List<GithubRepo>>> = MutableLiveData()

    fun loadHistory() = repository.getHistory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { state.value = State.Loading() }
            .doOnTerminate { state.value = State.Init() }
            .subscribe({
                it.takeIf { it.isNotEmpty() }
                        ?.let { state.value = State.Success(it) }
                        ?:let { state.value = State.Error("No recent repositories.") }
            },{
                state.value = State.Error(it.message ?: "Unexpected error")
            })
            .let { mDisposable.add(it) }

    fun clearSearchHistory() = repository.clearHistory()
            .subscribeOn(Schedulers.io())
            .subscribe(/* CAN DO STH LIKE IF COMPLETE SHOW MESSAGE */)
            .let { mDisposable.add(it) }
}
