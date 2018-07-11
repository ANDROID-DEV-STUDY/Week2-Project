package com.androidhuman.example.simplegithub.di.module

import android.content.Context
import com.androidhuman.example.simplegithub.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides @Singleton
    fun provideContext() : Context = app
}