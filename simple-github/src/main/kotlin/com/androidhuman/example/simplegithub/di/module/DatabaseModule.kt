package com.androidhuman.example.simplegithub.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.androidhuman.example.simplegithub.data.SearchHistoryDao
import com.androidhuman.example.simplegithub.data.SimpleGithubDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideSimpleGithubDatabase(context : Context) : SimpleGithubDatabase
            = Room.databaseBuilder(context, SimpleGithubDatabase::class.java, DB_NAME).build()

    @Provides
    @Singleton
    fun provideSearchHistoryDao(database : SimpleGithubDatabase) : SearchHistoryDao
            = database.searchHistoryDao()

    companion object {
        const val DB_NAME = "simple_github.db"
    }
}