package com.androidhuman.example.simplegithub.di.domain

import dagger.Module

@Module(includes = [
    AuthUseCaseModule::class,
    RepoUseCaseModule::class
])
class DomainModule {

}