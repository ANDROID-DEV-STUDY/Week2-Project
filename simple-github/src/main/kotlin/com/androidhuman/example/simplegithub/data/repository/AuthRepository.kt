package com.androidhuman.example.simplegithub.data.repository

import com.androidhuman.example.simplegithub.BuildConfig
import com.androidhuman.example.simplegithub.data.local.AuthLocalDataSource
import com.androidhuman.example.simplegithub.data.mapper.GitAuthMapper
import com.androidhuman.example.simplegithub.data.remote.AuthRemoteDataSource
import com.androidhuman.example.simplegithub.domain.gateway.GitAuthGateway
import com.androidhuman.example.simplegithub.entity.GithubAccessToken
import io.reactivex.Maybe
import io.reactivex.Single

class AuthRepository(
        private val localDataSource: AuthLocalDataSource,
        private val tokenRemoteDataSource: AuthRemoteDataSource) : GitAuthGateway {

    private val gitAuthMapper: GitAuthMapper = GitAuthMapper()

    override fun getAccessToken(): Maybe<GithubAccessToken> {

        return localDataSource.getAuthToken()
                .map { gitAuthMapper.toEntity(it)}
    }

    override fun getAccessTokenByCode(code: String): Single<GithubAccessToken> {

        return tokenRemoteDataSource.getAccessToken(BuildConfig.GITHUB_CLIENT_ID, BuildConfig.GITHUB_CLIENT_SECRET, code)
                .map {
                    gitAuthMapper.toEntity(it.accessToken)
                }.doOnSuccess {
                    localDataSource.updateToken(it.accessToken)
                }
    }
}