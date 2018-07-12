package com.androidhuman.example.simplegithub.ui.base

import io.reactivex.disposables.CompositeDisposable

abstract class RxBaseViewModel : BaseViewModel() {

    val disposable = CompositeDisposable()

    override fun onCleared() {
        disposable.clear()
    }
}