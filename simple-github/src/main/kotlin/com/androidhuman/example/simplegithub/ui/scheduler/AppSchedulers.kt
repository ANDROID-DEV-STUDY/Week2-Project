package com.androidhuman.example.simplegithub.ui.scheduler

import com.androidhuman.example.simplegithub.domain.Schedulers
import io.reactivex.Scheduler

class AppSchedulers: Schedulers {

    override val subscribeOn: Scheduler
        get() = io.reactivex.schedulers.Schedulers.io()

    override val observeOn: Scheduler
        get() = io.reactivex.android.schedulers.AndroidSchedulers.mainThread()
}