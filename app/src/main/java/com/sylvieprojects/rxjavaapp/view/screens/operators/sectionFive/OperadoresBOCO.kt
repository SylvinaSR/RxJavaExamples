package com.sylvieprojects.rxjavaapp.view.screens.operators.sectionFive

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.functions.BiFunction
import java.util.concurrent.TimeUnit


/**Operadores Booleanos y condicionales*/

@Composable
fun OperadoresBOCOScreen(modifier: Modifier = Modifier) {
    LaunchedEffect(Unit) {
        testReduce()
    }
}

private fun testReduce() {
    Log.d("BOCO", "----------Reduce------------")
    Observable.just(2,2,2,2)
        .reduce(object : BiFunction<Int, Int, Int> {
            override fun apply(t1: Int, t2: Int): Int {
                return t1 * t2
            }
        })
        .subscribe { e -> Log.d("BOCO", "Result: $e") }
}

private fun testCount() {
    Log.d("BOCO", "----------TestCount------------")
    val num = Observable.fromArray(1, 34, 55, 33, 567)
    num.count()
        .subscribe { e ->
            Log.d("BOCO", "count: $e")
        }
}

private fun testTakeWhile() {
    Log.d("BOCO", "----------TakeWhile------------")
    Observable.interval(400, TimeUnit.MILLISECONDS)
        .map { it.toInt() }
        .take(10)
        .takeWhile { e -> e <= 4 }
        .subscribe { e ->
            Log.d("BOCO", "onNext: $e")
        }
}

private fun testTakeUntil() {
    Log.d("BOCO", "----------TakeUntil------------")
    val observable1 = Observable.interval(500, TimeUnit.MILLISECONDS)
        .map { it.toInt() }
        .take(10)
    val observable2 = Observable.timer(3, TimeUnit.SECONDS)
        .map { 4 }
    observable1.takeUntil(observable2).subscribe { e -> Log.d("BOCO", "onNext: $e") }
}

private fun testSkipWhile() {
    Log.d("BOCO", "----------SkipWhile------------")
    Observable.interval(400, TimeUnit.MILLISECONDS)
        .map { it.toInt() }
        .take(10)
        .skipWhile { e -> e <= 4 }
        .subscribe { e ->
            Log.d("BOCO", "onNext: $e")
        }
}

private fun testSkipUntil() {
    Log.d("BOCO", "----------SkipUntil------------")
    val observable1 = Observable.interval(500, TimeUnit.MILLISECONDS)
        .map { it.toInt() }
        .take(10)

    val observable2 = Observable.timer(3, TimeUnit.SECONDS)
        .map { 4 }

    observable1
        .skipUntil(observable2)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { e ->
            Log.d("BOCO", "onNext: $e")
        }
}

private fun testSequenceEqual() {
    Log.d("BOCO", "----------SequenceEqual------------")
    val numObs1 = Observable.just(1, -1, 2, -6, 4, -78)
    val numObs2 = Observable.just(1, -1, 2, -6, 4, -78)
    Observable.sequenceEqual(numObs1, numObs2)
        .subscribe { e ->
            Log.d("BOCO", "onSuccess: $e")
        }
}

private fun testDefaultIfEmpty() {
    Log.d("BOCO", "----------DefaultIfEmpty------------")
    Observable.create(object : ObservableOnSubscribe<Int> {
        override fun subscribe(emitter: ObservableEmitter<Int>) {
            val num = 7
            if (num % 2 == 0) {
                emitter.onNext(num)
            }
            emitter.onComplete()
        }
    }).defaultIfEmpty(0)
        .subscribe { e ->
            Log.d("BOCO", "onSuccess: $e")
        }
}

private fun testContains() {
    Log.d("BOCO", "----------Contains------------")
    val numObservable = Observable.just(2, 0, -2, 66, 100, -478)
    numObservable.contains(-478)
        .subscribe { e ->
            Log.d("BOCO", "onSuccess: $e")
        }
}

private fun testAmb() {
    Log.d("BOCO", "----------Amb------------")
    val numObs1 = Observable.just(1, -1, 2, -6, 4, -78)
    val numObs2 = Observable.just(2, 0, -2, 66, 100, -478)
    Observable.ambArray(numObs1.delay(10, TimeUnit.SECONDS), numObs2)
        .subscribe { e ->
            Log.d("BOCO", "onNext: $e")
        }
}

private fun testAll() {
    Log.d("BOCO", "----------All------------")
    val numObservable = Observable.just(1, -1, 2, -6, 4, -78)
    numObservable.all { e -> e > 0 }
        .subscribe { e ->
            Log.d("BOCO", "onSucces: $e")
        }
}
