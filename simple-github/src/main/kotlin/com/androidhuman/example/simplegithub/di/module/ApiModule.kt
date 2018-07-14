package com.androidhuman.example.simplegithub.di.module

import android.content.Context
import com.androidhuman.example.simplegithub.data.local.AuthTokenProvider
import com.androidhuman.example.simplegithub.data.remote.AuthApi
import com.androidhuman.example.simplegithub.data.remote.AuthInterceptor
import com.androidhuman.example.simplegithub.data.remote.GithubApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides @Singleton
    fun provideAuthTokenProvider(context: Context): AuthTokenProvider
            = AuthTokenProvider(context)

    @Provides @Singleton
    fun provideAuthInterceptor(provider: AuthTokenProvider): AuthInterceptor {
        provider.token
                ?.let { return AuthInterceptor(it) }
        throw IllegalArgumentException("AuthToken cannot be null.")
    }

    @Provides @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor
            = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides @Singleton @Named("authClient")
    fun provideAuthOkHttpClient(httpInterceptor: HttpLoggingInterceptor) : OkHttpClient
            = OkHttpClient.Builder()
            .addInterceptor(httpInterceptor)
            .build()

    @Provides @Singleton @Named("githubClient")
    fun provideGithubOkHttpClient(httpInterceptor: HttpLoggingInterceptor, authInterceptor: AuthInterceptor): OkHttpClient
            = OkHttpClient.Builder()
                        .addInterceptor(httpInterceptor)
                        .addInterceptor(authInterceptor)
                        .build()

    @Provides @Singleton
    fun provideRxJava2CallAdapterFactory() : RxJava2CallAdapterFactory
            = RxJava2CallAdapterFactory.createAsync()

    @Provides @Singleton
    fun provideGsonConverterFactory() : GsonConverterFactory
            = GsonConverterFactory.create()

    @Provides @Singleton @Named("authRetrofit")
    fun provideAuthRetrofit(
            @Named("authClient")
            okHttpClient: OkHttpClient,
            rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
            gsonConverterFactory: GsonConverterFactory
    ): Retrofit
            = Retrofit.Builder()
            .baseUrl("https://github.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides @Singleton @Named("githubRetrofit")
    fun provideGithubRetrofit(
            @Named("githubClient")
            okHttpClient: OkHttpClient,
            rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
            gsonConverterFactory: GsonConverterFactory
    ): Retrofit
            = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides @Singleton
    fun provideAuthApi(@Named("authRetrofit") retrofit: Retrofit): AuthApi
            = retrofit.create(AuthApi::class.java)

    @Provides @Singleton
    fun provideGithubApi(@Named("githubRetrofit") retrofit: Retrofit): GithubApi
            = retrofit.create(GithubApi::class.java)

}