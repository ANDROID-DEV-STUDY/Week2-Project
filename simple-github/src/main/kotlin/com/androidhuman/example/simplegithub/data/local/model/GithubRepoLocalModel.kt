package com.androidhuman.example.simplegithub.data.local.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "repositories")
data class GithubRepoLocalModel(
        val name: String,
        @PrimaryKey @ColumnInfo(name = "full_name") val fullName: String,
        @Embedded val owner: GithubOwnerLocalModel,
        val description: String?,
        val language: String?,
        @ColumnInfo(name = "updated_at") val updatedAt: String,
        val stars: Int
)