package com.androidhuman.example.simplegithub.ui.search

import com.androidhuman.example.simplegithub.di.scope.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [SearchModule::class])
interface SearchComponent {

    fun inject(searchActivity: SearchActivity) : SearchActivity
}