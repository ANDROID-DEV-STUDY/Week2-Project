package com.androidhuman.example.simplegithub.ui.repo

import com.androidhuman.example.simplegithub.di.scope.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [RepositoryModule::class])
interface RepositoryComponent {

    fun inject(repositoryActivity: RepositoryActivity) : RepositoryActivity
}