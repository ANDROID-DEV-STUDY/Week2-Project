package com.androidhuman.example.simplegithub.domain.baseUseCase

import com.androidhuman.example.simplegithub.domain.Schedulers
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.observers.DisposableSingleObserver

abstract class UseCaseMaybe<Type, Params> {

    abstract fun buildUseCaseMaybe(params: Params?) : Maybe<Type>

    fun execute(
            params: Params?,
            schedulers: Schedulers,
            observer: DisposableMaybeObserver<Type>,
            addDisposable: (Disposable) -> Unit) {

        checkNotNull(observer)
        checkNotNull(addDisposable)

        val disposable : Disposable = buildUseCaseMaybe(params)
                .subscribeOn(schedulers.subscribeOn)
                .observeOn(schedulers.observeOn)
                .subscribeWith(observer)

        addDisposable(disposable)
    }
}