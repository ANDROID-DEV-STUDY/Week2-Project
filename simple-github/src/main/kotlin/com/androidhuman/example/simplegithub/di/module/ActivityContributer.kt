package com.androidhuman.example.simplegithub.di.module

import com.androidhuman.example.simplegithub.di.scope.ActivityScope
import com.androidhuman.example.simplegithub.ui.main.MainActivity
import com.androidhuman.example.simplegithub.ui.main.MainModule
import com.androidhuman.example.simplegithub.ui.repo.RepositoryActivity
import com.androidhuman.example.simplegithub.ui.repo.RepositoryModule
import com.androidhuman.example.simplegithub.ui.search.SearchActivity
import com.androidhuman.example.simplegithub.ui.search.SearchModule
import com.androidhuman.example.simplegithub.ui.signin.SignInActivity
import com.androidhuman.example.simplegithub.ui.signin.SignInModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityContributer {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivity() : MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [RepositoryModule::class])
    abstract fun contributeRepositoryActivity() : RepositoryActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [SearchModule::class])
    abstract fun contributeSearchActivity() : SearchActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [SignInModule::class])
    abstract fun contributeSignInActivity() : SignInActivity
}