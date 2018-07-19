package com.androidhuman.example.simplegithub.di

import android.app.Application
import android.content.Context
import com.androidhuman.example.simplegithub.domain.Schedulers
import com.androidhuman.example.simplegithub.ui.scheduler.AppSchedulers
import com.androidhuman.example.simplegithub.ui.scheduler.IoSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Named("appContext")
    @Singleton
    fun providesContext(application: Application) : Context = application.applicationContext

    @Provides
    @Named("appScheduler")
    @Singleton
    fun provideAppScheduler(): Schedulers = AppSchedulers()

    @Provides
    @Named("ioScheduler")
    @Singleton
    fun provideIoSchedulers(): Schedulers = IoSchedulers()
}