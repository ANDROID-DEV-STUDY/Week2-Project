package com.androidhuman.example.simplegithub.data.model

import com.google.gson.annotations.SerializedName

class GithubOwner(
        val login: String,
        @SerializedName("avatar_url") val avatarUrl: String)
