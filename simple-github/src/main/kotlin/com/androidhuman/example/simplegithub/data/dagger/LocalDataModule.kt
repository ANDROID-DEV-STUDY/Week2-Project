package com.androidhuman.example.simplegithub.data.dagger

import android.arch.persistence.room.Room
import android.content.Context
import com.androidhuman.example.simplegithub.data.local.sharedpreference.AuthTokenPreference
import com.androidhuman.example.simplegithub.data.local.dao.SearchHistoryDao
import com.androidhuman.example.simplegithub.data.local.database.SimpleGithubDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class LocalDataModule {
    @Provides
    @Singleton
    fun provideAuthTokenPreference(@Named("appContext") context: Context) : AuthTokenPreference
    = AuthTokenPreference(context)

    @Provides
    @Singleton
    fun provideSearchHistoryDao(db: SimpleGithubDatabase) : SearchHistoryDao
    = db.searchHistoryDao()

    @Provides
    @Singleton
    fun provideDatabase(@Named("appContext") context: Context) : SimpleGithubDatabase
    = Room.databaseBuilder(context, SimpleGithubDatabase::class.java, DB_NAME).build()

    companion object {
        const val DB_NAME = "simpe_github_db"
    }
}