package com.androidhuman.example.simplegithub.data.remote.model

import com.google.gson.annotations.SerializedName

class GithubOwnerRemoteModel(
        val login: String,
        @SerializedName("avatar_url") val avatarUrl: String)
