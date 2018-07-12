package com.androidhuman.example.simplegithub.di.module

import com.androidhuman.example.simplegithub.data.remote.AuthApi
import com.androidhuman.example.simplegithub.data.local.AuthTokenProvider
import com.androidhuman.example.simplegithub.di.scope.ActivityScope
import com.androidhuman.example.simplegithub.ui.signin.SignInViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    @ActivityScope
    fun provideSignInViewModelFactory(api: AuthApi, authTokenProvider: AuthTokenProvider) : SignInViewModelFactory
            = SignInViewModelFactory(api, authTokenProvider)

    // fun provideSignInViewModel(viewModelFactory: SignInViewModelFactory) : SignInViewModel?

}