package com.androidhuman.example.simplegithub.domain.baseUseCase

import com.androidhuman.example.simplegithub.domain.Schedulers
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.DisposableSubscriber

abstract class UseCaseFlowable<Type, in Params> {

    internal abstract fun buildUseCaseFlowable(params: Params?): Flowable<Type>

    fun execute(
            params: Params?,
            schedulers: Schedulers,
            subscriber: DisposableSubscriber<Type>): Disposable { // backpresure 를 지원하는 것이 subscriber => 따라서 Flowable 이 subscriber 를 사용함

        return buildUseCaseFlowable(params)
                .subscribeOn(schedulers.subscribeOn)
                .observeOn(schedulers.observeOn)
                .subscribeWith(subscriber)
    }
}