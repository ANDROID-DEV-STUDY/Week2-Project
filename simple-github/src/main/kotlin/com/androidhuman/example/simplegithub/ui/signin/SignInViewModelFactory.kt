package com.androidhuman.example.simplegithub.ui.signin

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.androidhuman.example.simplegithub.data.remote.api.AuthApi
import com.androidhuman.example.simplegithub.data.local.sharedpreference.AuthTokenPreference

class SignInViewModelFactory(
        val api: AuthApi,
        val authTokenPreference: AuthTokenPreference)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return SignInViewModel(api, authTokenPreference) as T
    }
}
