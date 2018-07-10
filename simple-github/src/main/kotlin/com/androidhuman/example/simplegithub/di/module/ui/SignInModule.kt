package com.androidhuman.example.simplegithub.di.module.ui

import com.androidhuman.example.simplegithub.api.AuthApi
import com.androidhuman.example.simplegithub.data.AuthTokenProvider
import com.androidhuman.example.simplegithub.ui.signin.SignInViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SignInModule {
    // SignInViewModelFactory 객체 제공
    @Provides
    fun provideViewModelFactory(authApi: AuthApi, authTokenProvider: AuthTokenProvider) : SignInViewModelFactory
    = SignInViewModelFactory(authApi, authTokenProvider)
}