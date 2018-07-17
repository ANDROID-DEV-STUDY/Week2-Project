package com.androidhuman.example.simplegithub.data.dagger

import dagger.Module

@Module(includes = [
    ApiModule::class,
    LocalDataModule::class,
    NetworkModule::class])
class DataModule {
}