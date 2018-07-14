package com.androidhuman.example.simplegithub.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.androidhuman.example.simplegithub.di.scope.ActivityScope
import com.androidhuman.example.simplegithub.ui.search.SearchAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [AdapterModule::class])
abstract class MainModule {

    @Binds @ActivityScope
    @Named("MainActivityContext")
    abstract fun provideContext(mainActivity: MainActivity): Context

    @Binds @ActivityScope
    abstract fun provideMainViewModelFactory(mainViewModelFactory: MainViewModelFactory): ViewModelProvider.Factory
}

@Module
class AdapterModule {

    @Provides @ActivityScope
    fun provideSearchAdapter(@Named("MainActivityContext") context : Context): SearchAdapter = SearchAdapter(context)
}