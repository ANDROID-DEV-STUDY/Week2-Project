package com.androidhuman.example.simplegithub.ui.signin

import android.arch.lifecycle.ViewModelProvider
import com.androidhuman.example.simplegithub.di.scope.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class SignInModule {

    @Binds @ActivityScope
    abstract fun provideSignInViewModelFactory(signInViewModelFactory: SignInViewModelFactory) : ViewModelProvider.Factory
}