package com.androidhuman.example.simplegithub.entity

data class GithubAuthToken (
        val accessToken: String,
        val scope: String,
        val tokenType: String)