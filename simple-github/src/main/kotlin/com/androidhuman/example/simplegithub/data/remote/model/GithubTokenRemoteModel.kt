package com.androidhuman.example.simplegithub.data.remote.model

import com.google.gson.annotations.SerializedName

class GithubTokenRemoteModel(
        @SerializedName("access_token") val accessToken: String,
        val scope: String,
        @SerializedName("token_type") val tokenType: String)
