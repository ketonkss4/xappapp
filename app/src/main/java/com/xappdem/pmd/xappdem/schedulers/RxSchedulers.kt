package com.xappdem.pmd.xappdem.schedulers

import io.reactivex.Scheduler

/**
 * This interface defines the blueprint for the RxSchedulers dependency
 * by obtaining the schedulers in this way, it makes testing easier as
 * this class can readily be mocked
 */
interface RxSchedulers {
    fun io(): Scheduler
    fun compute(): Scheduler
    fun uiThread(): Scheduler
    fun internet(): Scheduler
    fun runOnBackground(): Scheduler
}