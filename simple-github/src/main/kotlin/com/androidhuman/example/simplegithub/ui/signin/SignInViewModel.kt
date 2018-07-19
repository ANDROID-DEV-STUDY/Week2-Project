package com.androidhuman.example.simplegithub.ui.signin

import android.arch.lifecycle.MutableLiveData
import com.androidhuman.example.simplegithub.domain.Schedulers
import com.androidhuman.example.simplegithub.domain.interactor.auth.GetAccessToken
import com.androidhuman.example.simplegithub.domain.interactor.auth.GetAccessTokenByCode
import com.androidhuman.example.simplegithub.entity.GithubAccessToken
import com.androidhuman.example.simplegithub.ui.base.RxViewModel
import com.androidhuman.example.simplegithub.ui.scheduler.AppSchedulers
import com.androidhuman.example.simplegithub.util.State
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.observers.DisposableSingleObserver

class SignInViewModel(
        val getAccessToken: GetAccessToken,
        val getAccessTokenByCode: GetAccessTokenByCode,
        val schedulers: Schedulers)
    : RxViewModel() {

    val state: MutableLiveData<State<String>> = MutableLiveData()

    fun loadAccessToken() = getAccessToken
            .execute(null, schedulers, object: DisposableMaybeObserver<GithubAccessToken>() {

                override fun onSuccess(t: GithubAccessToken) {
                    state.value = State.Success(t.accessToken)
                }

                override fun onComplete() {
                    state.value = State.Error("Needs Auth in Github")
                }

                override fun onError(e: Throwable) {
                    state.value = State.Error(e.message ?: "Unexpected error")
                }

            })
            .bindUtilDestroy()

    fun requestAccessToken(code: String) = getAccessTokenByCode
            .execute(code, schedulers, object: DisposableSingleObserver<GithubAccessToken>() {

                override fun onStart() {
                    state.value = State.Loading()
                }

                override fun onSuccess(t: GithubAccessToken) {
                    state.value = State.Init()
                    state.value = State.Success(t.accessToken)
                }

                override fun onError(e: Throwable) {
                    state.value = State.Init()
                    state.value = State.Error(e.message ?: "Unexpected error")
                }

            })
            .bindUtilDestroy()

}
