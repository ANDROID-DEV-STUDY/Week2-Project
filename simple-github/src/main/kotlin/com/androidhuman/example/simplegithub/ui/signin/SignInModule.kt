package com.androidhuman.example.simplegithub.ui.signin

import android.arch.lifecycle.ViewModelProviders
import com.androidhuman.example.simplegithub.api.AuthApi
import com.androidhuman.example.simplegithub.data.AuthTokenProvider
import com.androidhuman.example.simplegithub.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class SignInModule(private val activity: SignInActivity) {

    @Provides
    fun provideActivity() : SignInActivity = activity

    @Provides
    fun provideSignInViewModelFactory(api : AuthApi, authTokenProvider: AuthTokenProvider) : SignInViewModelFactory
            = SignInViewModelFactory(api, authTokenProvider)

    @Provides @ActivityScope
    fun provideSignInViewModel(activity : SignInActivity, signInViewModelFactory: SignInViewModelFactory)
            = ViewModelProviders.of(activity, signInViewModelFactory)[SignInViewModel::class.java]
}