package com.androidhuman.example.simplegithub.ui.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

}

abstract class RxViewModel : BaseViewModel() {

    private val disposables = CompositeDisposable()

    fun Disposable.bindUtilDestroy() {
        if (!disposables.isDisposed) {
            disposables.add(this)
        } else {
            if (!isDisposed) {
                dispose()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}