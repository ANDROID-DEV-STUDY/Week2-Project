package com.androidhuman.example.simplegithub.ui.signin

import android.arch.lifecycle.MutableLiveData
import com.androidhuman.example.simplegithub.data.local.AuthTokenProvider
import com.androidhuman.example.simplegithub.data.remote.AuthApi
import com.androidhuman.example.simplegithub.ui.base.RxBaseViewModel
import com.androidhuman.example.simplegithub.util.State
import com.androidhuman.example.simplegithub.util.SupportOptional
import com.androidhuman.example.simplegithub.util.optionalOf
import io.reactivex.Single
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class SignInViewModel constructor(
        private val api: AuthApi,
        private val authTokenProvider: AuthTokenProvider
) : RxBaseViewModel() {

    val state: MutableLiveData<State<String>> = MutableLiveData()

    fun loadAccessToken()
            = Single.fromCallable { optionalOf(authTokenProvider.token) }
            .subscribeOn(Schedulers.io())
            .subscribe(Consumer<SupportOptional<String>> {
                state.value = State.Success(it.value)
            })
            .let { mDisposable.add(it) }

    fun requestAccessToken(clientId: String, clientSecret: String, code: String)
            = api.getAccessToken(clientId, clientSecret, code)
            .map { it.accessToken }
            .doOnSubscribe { state.value = State.Loading() }
            .doOnTerminate { state.value = State.Init() }
            .subscribe({ token ->
                authTokenProvider.updateToken(token)
                state.value = State.Success(token)
            }, {
                state.value = State.Error(it.message ?: "Unexpected error")
            })
            .let { mDisposable.add(it) }
}
