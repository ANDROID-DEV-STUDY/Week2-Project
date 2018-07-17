package com.androidhuman.example.simplegithub.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.androidhuman.example.simplegithub.data.local.dao.SearchHistoryDao
import com.androidhuman.example.simplegithub.data.local.model.GithubRepoLocalModel

@Database(entities = arrayOf(GithubRepoLocalModel::class), version = 1)
abstract class SimpleGithubDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao
}
