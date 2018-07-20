package com.androidhuman.example.simplegithub.domain.baseUseCase

import com.androidhuman.example.simplegithub.domain.Schedulers
import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver

abstract class UseCaseCompletable<in Params> {

    internal abstract fun buildUseCaseCompletable(params: Params?): Completable

    fun execute(
            params: Params?,
            schedulers: Schedulers,
            observer: DisposableCompletableObserver): Disposable {

        return if (schedulers.observeOn != null) {
            buildUseCaseCompletable(params)
                    .subscribeOn(schedulers.subscribeOn)
                    .observeOn(schedulers.observeOn)
                    .subscribeWith(observer)

        } else {
            buildUseCaseCompletable(params)
                    .subscribeOn(schedulers.subscribeOn)
                    .subscribeWith(observer)

        }
    }
}