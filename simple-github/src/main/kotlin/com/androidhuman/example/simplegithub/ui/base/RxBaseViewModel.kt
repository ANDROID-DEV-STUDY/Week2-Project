package com.androidhuman.example.simplegithub.ui.base

import io.reactivex.disposables.CompositeDisposable

abstract class RxBaseViewModel : BaseViewModel() {

    val mDisposable = CompositeDisposable()

    override fun onCleared() {
        mDisposable.clear()
    }
}