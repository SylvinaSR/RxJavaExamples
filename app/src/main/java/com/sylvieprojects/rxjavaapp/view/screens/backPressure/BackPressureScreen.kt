package com.sylvieprojects.rxjavaapp.view.screens.backPressure

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.reactivex.rxjava3.core.BackpressureOverflowStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

@Composable
fun BackPressureScreen(modifier: Modifier = Modifier) {

    LaunchedEffect(Unit) {
        backPressureBuffer()
    }

}

private fun backPressureBuffer() {
    val source: Flowable<Long> = Flowable.interval(1, TimeUnit.MILLISECONDS)
    Log.d("RxBP", "----------Buffer----------")
    source.onBackpressureBuffer(1000)
        .observeOn(Schedulers.computation())
        .subscribe(
            { e ->
                try {
                    Log.d("RxBP", "Consumiendo observable: $e")
                    Thread.sleep(100)
                } catch (e: Exception) {

                }
            }, { error ->
                Log.d("RxBP", "Error: $error")
            }
        )
}

private fun generateManualBackPressure() {
    val source: PublishSubject<Int> = PublishSubject.create()
    source.observeOn(Schedulers.io())
        .subscribe(
            { e -> operationLongDuration(e) },
            { error -> Log.d("RxBP", "Error: $error") },
            { Log.d("RxBP", "onComplete") }
        )
    for (i in 0..10) {
        source.onNext(i)
        Log.d("RxBP", "Creating Observable item: ${i + 1}")
    }
    source.onComplete()
}

private fun operationLongDuration(integer: Int) {
    try {
        Thread.sleep(1000)
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }
    Log.d("RxBP", "Consumiendo observable: $integer")
}