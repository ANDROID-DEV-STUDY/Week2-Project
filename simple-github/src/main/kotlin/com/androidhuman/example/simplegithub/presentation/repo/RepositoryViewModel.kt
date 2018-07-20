package com.androidhuman.example.simplegithub.presentation.repo

import android.arch.lifecycle.MutableLiveData
import com.androidhuman.example.simplegithub.domain.Schedulers
import com.androidhuman.example.simplegithub.domain.interactor.repo.GetRepo
import com.androidhuman.example.simplegithub.entity.GithubRepo
import com.androidhuman.example.simplegithub.presentation.base.RxViewModel
import com.androidhuman.example.simplegithub.util.State
import io.reactivex.observers.DisposableSingleObserver

class RepositoryViewModel(val getRepo: GetRepo,
                          val schedulers: Schedulers) : RxViewModel() {

    val state: MutableLiveData<State<GithubRepo>> = MutableLiveData()

    fun requestRepositoryInfo(login: String, repoName: String) = getRepo
            .execute(Pair(login, repoName),schedulers, object: DisposableSingleObserver<GithubRepo>() {
                override fun onStart() {
                    state.value = State.Loading()
                }

                override fun onSuccess(t: GithubRepo) {
                    state.value = State.Success(t)
                }

                override fun onError(e: Throwable) {
                    state.value = State.Error(e.message ?: "Unexpected error")
                }

            })
}
