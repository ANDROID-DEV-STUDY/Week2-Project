package com.androidhuman.example.simplegithub.ui.signin

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.androidhuman.example.simplegithub.data.remote.api.AuthApi
import com.androidhuman.example.simplegithub.data.local.sharedpreference.AuthTokenPreference
import com.androidhuman.example.simplegithub.domain.Schedulers
import com.androidhuman.example.simplegithub.domain.interactor.auth.GetAccessToken
import com.androidhuman.example.simplegithub.domain.interactor.auth.GetAccessTokenByCode
import com.androidhuman.example.simplegithub.ui.scheduler.AppSchedulers

class SignInViewModelFactory(
        val getAccessToken: GetAccessToken,
        val getAccessTokenByCode: GetAccessTokenByCode,
        val schedulers: Schedulers)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return SignInViewModel(getAccessToken, getAccessTokenByCode, schedulers) as T
    }
}
