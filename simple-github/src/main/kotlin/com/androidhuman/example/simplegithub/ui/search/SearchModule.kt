package com.androidhuman.example.simplegithub.ui.search

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.androidhuman.example.simplegithub.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [AdapterModule::class])
abstract class SearchModule {

    @Binds @ActivityScope @Named("SearchActivityContext")
    abstract fun provideContext(searchActivity: SearchActivity): Context

    @Binds @ActivityScope
    abstract fun provideSearchViewModelFactory(searchViewModelFactory: SearchViewModelFactory): ViewModelProvider.Factory
}

@Module
class AdapterModule {

    @Provides @ActivityScope
    fun provideAdapter(@Named("SearchActivityContext") context: Context): SearchAdapter = SearchAdapter(context)
}