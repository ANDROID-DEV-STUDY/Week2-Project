package com.androidhuman.example.simplegithub.ui.signin

import com.androidhuman.example.simplegithub.di.scope.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [SignInModule::class])
interface SignInComponent {

    fun inject(signInActivity: SignInActivity) : SignInActivity
}