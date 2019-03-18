package com.kotlinplaygroundmvvm.ui.util.Schedulers

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun io(): Scheduler
    fun main(): Scheduler
}