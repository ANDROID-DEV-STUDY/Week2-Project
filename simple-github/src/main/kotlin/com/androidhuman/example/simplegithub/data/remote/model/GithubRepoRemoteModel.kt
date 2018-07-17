package com.androidhuman.example.simplegithub.data.remote.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repositories")
class GithubRepoRemoteModel(
        val name: String,
        @SerializedName("full_name")
        @PrimaryKey @ColumnInfo(name = "full_name") val fullName: String,
        @Embedded val owner: GithubOwnerRemoteModel,
        val description: String?,
        val language: String?,
        @SerializedName("updated_at") @ColumnInfo(name = "updated_at") val updatedAt: String,
        @SerializedName("stargazers_count") val stars: Int)
