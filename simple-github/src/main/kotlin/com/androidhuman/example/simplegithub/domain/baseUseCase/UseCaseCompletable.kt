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
            observer: DisposableCompletableObserver,
            addDisposable: (Disposable) -> Unit) {

        checkNotNull(addDisposable)
        checkNotNull(observer)

        schedulers.observeOn?.let {
            val disposable: Disposable = buildUseCaseCompletable(params)
                    .subscribeOn(schedulers.subscribeOn)
                    .observeOn(schedulers.observeOn)
                    .subscribeWith(observer)

            addDisposable(disposable)

        }.let{
            val disposable: Disposable = buildUseCaseCompletable(params)
                    .subscribeOn(schedulers.subscribeOn)
                    .subscribeWith(observer)

            addDisposable(disposable)

        }
    }
}