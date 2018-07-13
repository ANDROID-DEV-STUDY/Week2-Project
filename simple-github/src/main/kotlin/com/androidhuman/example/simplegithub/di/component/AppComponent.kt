package com.androidhuman.example.simplegithub.di.component

import com.androidhuman.example.simplegithub.GitApplication
import com.androidhuman.example.simplegithub.di.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ApiModule::class,
    DatabaseModule::class,
    RepositoryModule::class,
    AndroidSupportInjectionModule::class,
    ActivityContributer::class
])
interface AppComponent: AndroidInjector<GitApplication> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<GitApplication>()
}