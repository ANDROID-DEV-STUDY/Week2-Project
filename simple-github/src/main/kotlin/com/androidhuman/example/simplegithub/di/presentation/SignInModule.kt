package com.androidhuman.example.simplegithub.di.presentation

import com.androidhuman.example.simplegithub.data.remote.api.AuthApi
import com.androidhuman.example.simplegithub.data.local.sharedpreference.AuthTokenPreference
import com.androidhuman.example.simplegithub.ui.signin.SignInViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SignInModule {
    // SignInViewModelFactory 객체 제공
    @Provides
    fun provideViewModelFactory(authApi: AuthApi, authTokenPreference: AuthTokenPreference) : SignInViewModelFactory
    = SignInViewModelFactory(authApi, authTokenPreference)
}