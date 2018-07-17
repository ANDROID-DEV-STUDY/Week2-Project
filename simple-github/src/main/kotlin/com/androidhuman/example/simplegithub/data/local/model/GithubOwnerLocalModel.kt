package com.androidhuman.example.simplegithub.data.local.model

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class GithubOwnerLocalModel(
        val login: String,
        @ColumnInfo(name = "avatar_url") val avatarUrl: String)
