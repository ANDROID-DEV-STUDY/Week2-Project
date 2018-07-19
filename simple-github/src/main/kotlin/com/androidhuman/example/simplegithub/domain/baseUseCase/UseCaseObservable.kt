package com.androidhuman.example.simplegithub.domain.baseUseCase

import com.androidhuman.example.simplegithub.domain.Schedulers
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

abstract class UseCaseObservable<Type, Params> {

    abstract fun buildUseCaseObservable(params: Params?): Observable<Type>

    fun execute(
            params: Params?,
            schedulers: Schedulers,
            observer: DisposableObserver<Type>,
            addDisposable: (Disposable) -> Unit) {

        checkNotNull(observer)
        checkNotNull(addDisposable)

        val disposable: Disposable = buildUseCaseObservable(params)
                .subscribeOn(schedulers.subscribeOn)
                .observeOn(schedulers.observeOn)
                .subscribeWith(observer)

        addDisposable(disposable)
    }
}
