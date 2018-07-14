package com.androidhuman.example.simplegithub.ui.repo

import android.arch.lifecycle.MutableLiveData
import com.androidhuman.example.simplegithub.data.model.GithubRepo
import com.androidhuman.example.simplegithub.data.repository.RepoRepository
import com.androidhuman.example.simplegithub.ui.base.RxBaseViewModel
import com.androidhuman.example.simplegithub.util.State
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepositoryViewModel constructor(
        private val repository: RepoRepository
) : RxBaseViewModel() {

    val state: MutableLiveData<State<GithubRepo>> = MutableLiveData()

    fun requestRepositoryInfo(login: String, repoName: String)
        = repository.getRepository(login, repoName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { state.value = State.Loading() }
                .doOnTerminate { state.value = State.Init() }
                .subscribe({
                    if(it != null) state.value = State.Success(it)
                    else state.value = State.Error("")
                },{
                    state.value = State.Error(it.message ?: "Unexpected Error")
                })
                .let { mDisposable.add(it) }
}
