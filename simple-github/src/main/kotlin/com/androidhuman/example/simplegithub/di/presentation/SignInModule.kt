package com.androidhuman.example.simplegithub.di.presentation

import com.androidhuman.example.simplegithub.data.remote.api.AuthApi
import com.androidhuman.example.simplegithub.data.local.sharedpreference.AuthTokenPreference
import com.androidhuman.example.simplegithub.domain.Schedulers
import com.androidhuman.example.simplegithub.domain.interactor.auth.GetAccessToken
import com.androidhuman.example.simplegithub.domain.interactor.auth.GetAccessTokenByCode
import com.androidhuman.example.simplegithub.ui.scheduler.AppSchedulers
import com.androidhuman.example.simplegithub.ui.signin.SignInViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SignInModule {
    // SignInViewModelFactory 객체 제공
    @Provides
    fun provideViewModelFactory(getAccessToken: GetAccessToken,
                                getAccessTokenByCode: GetAccessTokenByCode,
                                @Named("appScheduler")schedulers: Schedulers) : SignInViewModelFactory
    = SignInViewModelFactory(getAccessToken, getAccessTokenByCode, schedulers)
}