package com.androidhuman.example.simplegithub.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.androidhuman.example.simplegithub.data.model.GithubRepo

@Database(entities = [GithubRepo::class], version = 1)
abstract class SimpleGithubDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao
}
