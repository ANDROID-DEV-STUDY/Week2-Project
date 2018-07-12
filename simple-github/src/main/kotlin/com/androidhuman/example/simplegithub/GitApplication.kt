package com.androidhuman.example.simplegithub

import com.androidhuman.example.simplegithub.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class GitApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>
        = DaggerAppComponent.builder().create(this)
}