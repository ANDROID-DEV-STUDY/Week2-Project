package com.androidhuman.example.simplegithub.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.androidhuman.example.simplegithub.data.local.model.GithubRepoLocalModel
import io.reactivex.Flowable

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(repo: GithubRepoLocalModel)

    @Query("SELECT * FROM repositories")
    fun getHistory(): Flowable<List<GithubRepoLocalModel>>

    @Query("DELETE FROM repositories")
    fun clearAll()
}
