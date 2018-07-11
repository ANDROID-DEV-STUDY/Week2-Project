package com.androidhuman.example.simplegithub.di.component

import com.androidhuman.example.simplegithub.di.module.ApiModule
import com.androidhuman.example.simplegithub.di.module.AppModule
import com.androidhuman.example.simplegithub.di.module.DatabaseModule
import com.androidhuman.example.simplegithub.ui.main.MainComponent
import com.androidhuman.example.simplegithub.ui.main.MainModule
import com.androidhuman.example.simplegithub.ui.repo.RepositoryComponent
import com.androidhuman.example.simplegithub.ui.repo.RepositoryModule
import com.androidhuman.example.simplegithub.ui.search.SearchComponent
import com.androidhuman.example.simplegithub.ui.search.SearchModule
import com.androidhuman.example.simplegithub.ui.signin.SignInComponent
import com.androidhuman.example.simplegithub.ui.signin.SignInModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ApiModule::class,
    DatabaseModule::class
])
interface AppComponent {

    fun plus(signInModule: SignInModule) : SignInComponent

    fun plus(mainModule: MainModule) : MainComponent

    fun plus(repositoryModule: RepositoryModule) : RepositoryComponent

    fun plus(searchModule: SearchModule) : SearchComponent
}