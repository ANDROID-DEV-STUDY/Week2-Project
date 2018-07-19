package com.androidhuman.example.simplegithub.di.domain

import com.androidhuman.example.simplegithub.domain.gateway.GitAuthGateway
import com.androidhuman.example.simplegithub.domain.interactor.auth.GetAccessToken
import com.androidhuman.example.simplegithub.domain.interactor.auth.GetAccessTokenByCode
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthUseCaseModule {

    @Provides
    @Singleton
    fun providesGetAccessTokenUseCase(gateway: GitAuthGateway): GetAccessToken = GetAccessToken(gateway)

    @Provides
    @Singleton
    fun provideGetAccessTokenByCodeUseCase(gateway: GitAuthGateway): GetAccessTokenByCode = GetAccessTokenByCode(gateway)
}