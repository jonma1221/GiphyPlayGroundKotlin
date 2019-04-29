package com.kotlinplaygroundmvvm.ui.util.Schedulers

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class SchedulerProviderTrampoline: SchedulerProvider {
    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun main(): Scheduler {
        return Schedulers.trampoline()
    }
}