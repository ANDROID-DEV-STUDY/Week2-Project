package com.androidhuman.example.simplegithub.data.remote.model

import com.google.gson.annotations.SerializedName

class RepoResponseModel(
        @SerializedName("total_count") val totalCount: Int,
        val items: List<GithubRepoRemoteModel>)
