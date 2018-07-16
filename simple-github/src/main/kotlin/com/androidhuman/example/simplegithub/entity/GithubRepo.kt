package com.androidhuman.example.simplegithub.entity

data class GithubRepo (
    val name: String,
    val fullName: String,
    val owner: GithubOwner,
    val description: String?,
    val language: String?,
    val updatedAt: String,
    val stars: Int
)