package com.androidhuman.example.simplegithub.presentation.scheduler

import com.androidhuman.example.simplegithub.domain.Schedulers
import io.reactivex.Scheduler

class IoSchedulers: Schedulers {

    override val subscribeOn: Scheduler
        get() = io.reactivex.schedulers.Schedulers.io()

    override val observeOn: Scheduler? = null
}