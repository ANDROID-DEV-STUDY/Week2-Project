package com.androidhuman.example.simplegithub.ui.signin

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.androidhuman.example.simplegithub.api.AuthApi
import com.androidhuman.example.simplegithub.data.AuthTokenProvider

class SignInViewModelFactory(
        private val api: AuthApi,
        private val authTokenProvider: AuthTokenProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return SignInViewModel(api, authTokenProvider) as T
    }
}
