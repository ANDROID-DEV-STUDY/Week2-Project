package com.androidhuman.example.simplegithub.domain.baseUseCase

import com.androidhuman.example.simplegithub.domain.Schedulers
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver

abstract class UseCaseSingle<Type, Params> {

    abstract fun buildUseCaseSingle(params: Params?) : Single<Type>

    fun execute(
            params: Params?,
            schedulers: Schedulers,
            observer: DisposableSingleObserver<Type>,
            addDisposable: (Disposable) -> Unit
    ) {
        checkNotNull(observer)
        checkNotNull(addDisposable)

        val disposable : Disposable = buildUseCaseSingle(params)
                .subscribeOn(schedulers.subscribeOn)
                .observeOn(schedulers.observeOn)
                .subscribeWith(observer)

        addDisposable(disposable)
    }
}