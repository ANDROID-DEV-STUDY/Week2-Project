package com.androidhuman.example.simplegithub.di.presentation

import com.androidhuman.example.simplegithub.di.presentation.MainModule
import com.androidhuman.example.simplegithub.di.presentation.RepositoryModule
import com.androidhuman.example.simplegithub.di.presentation.SearchModule
import com.androidhuman.example.simplegithub.di.presentation.SignInModule
import com.androidhuman.example.simplegithub.ui.main.MainActivity
import com.androidhuman.example.simplegithub.ui.repo.RepositoryActivity
import com.androidhuman.example.simplegithub.ui.search.SearchActivity
import com.androidhuman.example.simplegithub.ui.signin.SignInActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

// ContributesAndroidInjector : 특정 액티비티를 객체 그래프에 추가
// 위에서 모듈을 추가해서 객체 그래프에 추가 가능

@Module
abstract class ActivityBinder {
    @ContributesAndroidInjector(modules = [SignInModule::class])
    abstract fun bindSignInActivity(): SignInActivity

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity() : MainActivity

    @ContributesAndroidInjector(modules = [SearchModule::class])
    abstract fun bindSearchActivity() : SearchActivity

    @ContributesAndroidInjector(modules = [RepositoryModule::class])
    abstract fun bindRepositoryActivity() : RepositoryActivity

}