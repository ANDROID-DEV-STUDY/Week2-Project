package com.androidhuman.example.simplegithub.presentation.main

import android.arch.lifecycle.MutableLiveData
import com.androidhuman.example.simplegithub.domain.Schedulers
import com.androidhuman.example.simplegithub.domain.interactor.repo.ClearHistoryRepos
import com.androidhuman.example.simplegithub.domain.interactor.repo.GetHistoryRepos
import com.androidhuman.example.simplegithub.entity.GithubRepo
import com.androidhuman.example.simplegithub.presentation.base.RxViewModel
import com.androidhuman.example.simplegithub.util.State
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.subscribers.DisposableSubscriber

class MainViewModel(
        val getHistoryRepos: GetHistoryRepos,
        val clearHistoryRepos: ClearHistoryRepos,
        val appSchedulers: Schedulers,
        val ioSchedulers: Schedulers)
    : RxViewModel() {

    val stateGetHistory: MutableLiveData<State<List<GithubRepo>>> = MutableLiveData()
    val stateClearHistory: MutableLiveData<State<String>> = MutableLiveData()

    fun searchHistory() = getHistoryRepos
            .execute(null, appSchedulers, object: DisposableSubscriber<List<GithubRepo>>(){

                override fun onStart() {
                    super.onStart()
                    stateGetHistory.value = State.Loading()
                }

                override fun onComplete() {
                    // no-op
                }

                override fun onNext(t: List<GithubRepo>?) {
                    stateGetHistory.value = State.Success(t.orEmpty())
                }

                override fun onError(t: Throwable?) {
                    stateGetHistory.value = State.Error(t?.message ?: "Unexpected error")
                }

            }).bindUtilDestroy()


    fun clearSearchHistory() = clearHistoryRepos
            .execute(null, ioSchedulers, object: DisposableCompletableObserver(){
                override fun onStart() {
                    stateClearHistory.postValue(State.Loading())
                }

                override fun onComplete() {
                    stateClearHistory.postValue(State.Success("Success"))
                }

                override fun onError(e: Throwable) {
                    stateGetHistory.postValue(State.Error(e.message ?: "Unexpected error"))
                }

            }).bindUtilDestroy()
}
