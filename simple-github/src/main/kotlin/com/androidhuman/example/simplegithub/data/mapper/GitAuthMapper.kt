package com.androidhuman.example.simplegithub.data.mapper

import com.androidhuman.example.simplegithub.data.remote.model.GithubTokenRemoteModel
import com.androidhuman.example.simplegithub.entity.GithubAccessToken

class GitAuthMapper {

    fun toEntity(authToken: String): GithubAccessToken
            = GithubAccessToken(authToken)

    fun toLocal(tokenRemoteModel: GithubTokenRemoteModel): String
            = tokenRemoteModel.accessToken
}