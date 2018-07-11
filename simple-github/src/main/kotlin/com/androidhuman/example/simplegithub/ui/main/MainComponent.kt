package com.androidhuman.example.simplegithub.ui.main

import com.androidhuman.example.simplegithub.di.scope.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity) : MainActivity
}