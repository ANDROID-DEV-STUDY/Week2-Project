package com.androidhuman.example.simplegithub.data.mapper

import com.androidhuman.example.simplegithub.data.local.model.GithubOwnerLocalModel
import com.androidhuman.example.simplegithub.data.local.model.GithubRepoLocalModel
import com.androidhuman.example.simplegithub.data.remote.model.GithubOwnerRemoteModel
import com.androidhuman.example.simplegithub.data.remote.model.GithubRepoRemoteModel
import com.androidhuman.example.simplegithub.entity.GithubOwner
import com.androidhuman.example.simplegithub.entity.GithubRepo

class GitRepoMapper {

    fun toEntity(ownerLocalModel: GithubOwnerLocalModel)
            = GithubOwner(ownerLocalModel.login, ownerLocalModel.avatarUrl)

    fun toEntity(ownerRemoteModel: GithubOwnerRemoteModel)
            = GithubOwner(ownerRemoteModel.login, ownerRemoteModel.avatarUrl)

    fun toEntity(repoLocalModel: GithubRepoLocalModel)
            = GithubRepo(repoLocalModel.name, repoLocalModel.fullName, toEntity(repoLocalModel.owner),
                    repoLocalModel.description, repoLocalModel.language, repoLocalModel.updatedAt, repoLocalModel.stars)

    fun toEntity(repoRemoteModel: GithubRepoRemoteModel)
            = GithubRepo(repoRemoteModel.name, repoRemoteModel.fullName, toEntity(repoRemoteModel.owner),
            repoRemoteModel.description, repoRemoteModel.language, repoRemoteModel.updatedAt, repoRemoteModel.stars)
}