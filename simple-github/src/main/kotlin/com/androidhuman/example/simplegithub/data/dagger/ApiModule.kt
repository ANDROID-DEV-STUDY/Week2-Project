package com.androidhuman.example.simplegithub.data.dagger

import com.androidhuman.example.simplegithub.data.remote.api.AuthApi
import com.androidhuman.example.simplegithub.data.remote.api.GithubApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    // AuthApi 객체 제공
    @Provides
    @Singleton // 싱글턴으로 적용
    fun provideAuthApi(
            @Named("unauthorized")okHttpClient: OkHttpClient, // named 로 객체의 특성을 다르게 해서 대입할 수 있다.
            callAdapter: CallAdapter.Factory,
            converter: Converter.Factory) : AuthApi
    = Retrofit.Builder()
            .baseUrl("https://github.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(converter)
            .build()
            .create(AuthApi::class.java)

    // GithubApi 객체 제공
    @Provides
    @Singleton
    fun provideGithubApi(
            @Named("authorized")okHttpClient: OkHttpClient,
            callAdapter: CallAdapter.Factory,
            converter: Converter.Factory) : GithubApi
    = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(converter)
            .build()
            .create(GithubApi::class.java)

    // CallAdapter.Factory 객체 제공
    @Provides
    @Singleton
    fun provideCallAdapterFactory() : CallAdapter.Factory = RxJava2CallAdapterFactory.createAsync()

    // Converter.Factory 객체 제공
    @Provides
    @Singleton
    fun provideConverterFactory() : Converter.Factory = GsonConverterFactory.create()
}