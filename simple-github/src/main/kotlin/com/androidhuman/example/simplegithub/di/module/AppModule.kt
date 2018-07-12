package com.androidhuman.example.simplegithub.di.module

import android.content.Context
import com.androidhuman.example.simplegithub.GitApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds @Singleton
    abstract fun provideContext(application: GitApplication) : Context
}