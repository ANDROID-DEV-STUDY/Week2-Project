package com.androidhuman.example.simplegithub.di.component

import com.androidhuman.example.simplegithub.GitApplication
import com.androidhuman.example.simplegithub.di.module.ActivityContributer
import com.androidhuman.example.simplegithub.di.module.ApiModule
import com.androidhuman.example.simplegithub.di.module.AppModule
import com.androidhuman.example.simplegithub.di.module.DatabaseModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    AndroidSupportInjectionModule::class,
    ActivityContributer::class,
    ApiModule::class,
    DatabaseModule::class
])
interface AppComponent: AndroidInjector<GitApplication> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<GitApplication>()
}