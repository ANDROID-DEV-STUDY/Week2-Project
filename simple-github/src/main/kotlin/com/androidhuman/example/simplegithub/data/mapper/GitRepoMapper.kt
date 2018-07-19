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

    fun toLocal(ownerRemoteModel: GithubOwnerRemoteModel)
            = GithubOwnerLocalModel(ownerRemoteModel.login, ownerRemoteModel.avatarUrl)

    fun toLocal(repoRemoteModel: GithubRepoRemoteModel)
            = GithubRepoLocalModel(repoRemoteModel.name, repoRemoteModel.fullName, toLocal(repoRemoteModel.owner),
            repoRemoteModel.description, repoRemoteModel.language, repoRemoteModel.updatedAt, repoRemoteModel.stars)

    fun toLocal(ownerEntity: GithubOwner)
            = GithubOwnerLocalModel(ownerEntity.login, ownerEntity.avatarUrl)

    fun toLocal(repoEntity: GithubRepo)
            = GithubRepoLocalModel(repoEntity.name, repoEntity.fullName, toLocal(repoEntity.owner),
            repoEntity.description, repoEntity.language, repoEntity.updatedAt, repoEntity.stars)
}