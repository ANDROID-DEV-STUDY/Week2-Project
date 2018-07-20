package com.androidhuman.example.simplegithub.presentation.search

import android.arch.lifecycle.MutableLiveData
import com.androidhuman.example.simplegithub.data.remote.model.GithubRepoRemoteModel
import com.androidhuman.example.simplegithub.domain.Schedulers
import com.androidhuman.example.simplegithub.domain.interactor.repo.SaveRepo
import com.androidhuman.example.simplegithub.domain.interactor.repo.SearchReposByName
import com.androidhuman.example.simplegithub.entity.GithubRepo
import com.androidhuman.example.simplegithub.presentation.base.RxViewModel
import com.androidhuman.example.simplegithub.util.State
import com.androidhuman.example.simplegithub.util.SupportOptional
import com.androidhuman.example.simplegithub.util.emptyOptional
import com.androidhuman.example.simplegithub.util.optionalOf
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.BehaviorSubject

class SearchViewModel(
        val searchReposByName: SearchReposByName,
        val saveRepo: SaveRepo,
        val appSchedulers: Schedulers,
        val ioSchedulers: Schedulers)
    : RxViewModel() {

    val stateSearchRepos: MutableLiveData<State<List<GithubRepo>>> = MutableLiveData()
    val stateSaveRepo: MutableLiveData<State<String>> = MutableLiveData()
    val lastSearchKeyword: MutableLiveData<String> = MutableLiveData()

    fun searchRepository(query: String) = searchReposByName
            .execute(query, appSchedulers, object: DisposableObserver<List<GithubRepo>>(){

                override fun onStart() {
                    stateSearchRepos.value = State.Loading()
                }

                override fun onComplete() {
                    lastSearchKeyword.value = query
                }

                override fun onNext(t: List<GithubRepo>) {
                    stateSearchRepos.value = State.Success(t)
                }

                override fun onError(e: Throwable) {
                    stateSearchRepos.value = State.Error(e.message ?: "Unexpected error")
                }

            }).bindUtilDestroy()

    fun addToSearchHistory(repository: GithubRepo) = saveRepo
            .execute(repository, ioSchedulers, object: DisposableCompletableObserver(){
                override fun onComplete() {
                    stateSaveRepo.postValue(State.Success("Success"))
                }

                override fun onError(e: Throwable) {
                    stateSaveRepo.postValue(State.Error(e.message ?: "Unexpected error"))
                }

            }).bindUtilDestroy()
}
