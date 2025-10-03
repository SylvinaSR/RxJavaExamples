package com.sylvieprojects.rxjavaapp.view.screens.rxBus

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject


class RXBusExample private constructor() {

    private val bus: PublishSubject<Any> = PublishSubject.create()

    fun setEvents(message: Any) {
        bus.onNext(message)
    }

    fun getEvents(): Observable<Any> {
        return bus
    }

    companion object {
        private var instance: RXBusExample? = null

        fun getInstance(): RXBusExample {
            return instance ?: synchronized(this) {
                instance ?: RXBusExample().also { instance = it }
            }
        }
    }
}
