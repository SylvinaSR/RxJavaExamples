package com.sylvieprojects.rxjavaapp.view.screens.operators.sectionFive

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Timed
import io.reactivex.rxjava3.subjects.Subject
import java.util.concurrent.TimeUnit

@Composable
fun OperadoresMOSOScreen(modifier: Modifier = Modifier) {
    LaunchedEffect(Unit) {
        testUsing()
    }
}

private fun testUsing() {
    Log.d("MOSO", "----------Using------------")
    Observable.using(
        { "Using" }, // Supplier: devuelve el recurso
        { s ->        // Function: crea un Observable con ese recurso
            Observable.create<Char> { emitter ->
                for (c in s.toCharArray()) {
                    emitter.onNext(c)
                }
                emitter.onComplete()
            }
        },
        { resource -> // Consumer: libera el recurso (opcional aquí)
            Log.d("MOSO", "Recurso liberado: $resource")
        }
    ).subscribe(
        { char -> Log.d("MOSO", "Letra: $char") },
        { error -> Log.e("MOSO", "Error: ${error.message}") },
        { Log.d("MOSO", "Completado") }
    )
}

private fun testTimeStamp() {
    Log.d("MOSO", "----------TimeStamp------------")
    val testObservable = Observable.create<String> { emitter ->
        emitter.onNext("A")
        emitter.onNext("B")
        emitter.onNext("C")
    }
    testObservable.timestamp()
        .subscribe(object : Subject<Timed<String>>() {
            override fun hasObservers(): Boolean {
                return false
            }

            override fun hasThrowable(): Boolean {
                return false
            }

            override fun hasComplete(): Boolean {
                return false
            }

            override fun getThrowable(): Throwable? {
                return null
            }

            override fun subscribeActual(observer: Observer<in Timed<String>>) {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Timed<String>) {
                Log.d("MOSO", "TimeStamp: ${t}")
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }
        })
}

private fun testTimeOut() {
    Log.d("MOSO", "----------TimeOut------------")
    val testObservable = Observable.create<String> { emitter ->
        emitter.onNext("A")
        emitter.onNext("B")
        emitter.onNext("C")
    }
    testObservable
        .concatMap { item ->
            Observable.just(item).delay(1, TimeUnit.SECONDS)
        }
        .timeout(500, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.single())
        .subscribe(
            { item -> Log.d("MOSO", "onNext: $item") },
            { error -> Log.d("MOSO", "error: $error") }
        )
}

private fun testTimeInterval() {
    Log.d("MOSO", "----------TimeInterval------------")
    val letrasObservable = Observable.create<String>(
        ObservableOnSubscribe { e: ObservableEmitter<String> ->
            e.onNext("A")
            e.onNext("B")
            e.onNext("C")
        }
    )
    Observable.interval(1000, TimeUnit.MILLISECONDS)
        .take(3)
        .timeInterval()
        .subscribe(object : Subject<Timed<Long>>() {
            override fun hasObservers(): Boolean {
                return false
            }

            override fun hasThrowable(): Boolean {
                return false
            }

            override fun hasComplete(): Boolean {
                return false
            }

            override fun getThrowable(): Throwable? {
                return null
            }

            override fun subscribeActual(observer: Observer<in Timed<Long>>) {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Timed<Long>) {
                Log.d("MOSO", "TimeInterval: ${t}")
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }
        })

}

private fun testSubscribeOnObserveOn() {
    Log.d("MOSO", "----------subscribeOn observeOn------------")
    val observable = Observable.create<String> { emitter ->
        Log.d("MOSO", "Observable ejecutandose en hilo: ${Thread.currentThread().name}")
        emitter.onNext("Emitiendo Item")
    }
    observable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { e ->
            Log.d("MOSO", "Observer ejecutandose en hilo: ${Thread.currentThread().name}")
        }
}

private fun testDo() {
    Log.d("MOSO", "----------Do------------")
    val numeroObservable = Observable.just("1", "4", "89", "45", "0")
    numeroObservable
        .doOnNext { e -> Log.d("MOSO", "Do doOnNext: $e") }
        .doAfterNext { e -> Log.d("MOSO", "Do doAfterNext: $e") }
        .doOnComplete { Log.d("MOSO", "Do doOnComplete") }
        .subscribe { result ->
            Log.d("MOSO", "Delay onNext Result: $result")
        }
}

private fun testDelay() {
    Log.d("MOSO", "----------Delay------------")
    val numeroObservable = Observable.just("1", "4", "89", "45", "0")
    numeroObservable.delay(5, TimeUnit.SECONDS)
        .subscribe { result ->
            Log.d("MOSO", "Delay onNext Result: $result")
        }
}

private fun testReturnWhen() {
    Log.d("MOSO", "----------ReturnWhen------------")
    Observable.create<String> { emitter ->
        emitter.onNext("Testing RetryWhen")
        emitter.onError(Throwable("Test"))
    }.retryWhen { errors ->
        errors
            .zipWith(Observable.range(1, 3)) { e, retryCount -> retryCount }
            .flatMap { retryCount ->
                Log.d("MOSO", "Retry attempt #$retryCount")
                Observable.timer(1, TimeUnit.SECONDS)
            }
    }.subscribe(
        { next -> Log.d("MOSO", "Zip onNext Result: $next") },
        { error -> Log.d("MOSO", "Zip onError Result: $error") },
        { Log.d("MOSO", "Zip onComplete") }
    )
}

private fun testZip() {
    Log.d("MOSO", "----------Zip------------")
    val observable1 = Observable.interval(1, TimeUnit.SECONDS).take(5)
        .map { e -> "Group1: $e" }
    val observable2 = Observable.interval(1, TimeUnit.SECONDS).take(5)
        .map { e -> "Group2: $e" }
    Observable.zip(observable1, observable2) { x, y ->
        "$x - $y"
    }.subscribe { result ->
        Log.d("MOSO", "Zip onNext Result: $result")
    }
}

private fun testMerge() {
    Log.d("MOSO", "----------Merge------------")
    val observable1 = Observable.interval(2, TimeUnit.SECONDS).take(5)
        .map { e -> "Group1: $e" }
    val observable2 = Observable.interval(1, TimeUnit.SECONDS).take(5)
        .map { e -> "Group2: $e" }
    Observable.merge(observable1, observable2).subscribe { result ->
        Log.d("MOSO", "Merge onNext Result: $result")
    }
}

private fun testJoin() {
    Log.d("MOSO", "----------Join------------")
    val left = Observable.interval(100, TimeUnit.MILLISECONDS).take(10)
    val right = Observable.interval(100, TimeUnit.MILLISECONDS).take(10)

    val leftWindow = 0L
    val rightWindow = 0L

    left.join(
        right,
        { Observable.timer(leftWindow, TimeUnit.MILLISECONDS) },
        { Observable.timer(rightWindow, TimeUnit.MILLISECONDS) },
        { l, r ->
            Log.d("MOSO", "Join combining Left: $l and Right: $r")
            l + r
        }
    ).subscribe { result ->
        Log.d("MOSO", "Join onNext Result: $result")
    }
}

private fun testCombineLatest() {
    Log.d("MOSO", "----------CombineLatest------------")
    val obs1 = Observable.interval(25, TimeUnit.MILLISECONDS).take(3)
    val obs2 = Observable.interval(12, TimeUnit.MILLISECONDS).take(5)
    Observable.combineLatest(obs1, obs2) { valOne, valTwo ->
        "Observable1: $valOne, Observable2: $valTwo"
    }.subscribe { e ->
        Log.d("MOSO", "CombineLatests onNext: $e")
    }
}
