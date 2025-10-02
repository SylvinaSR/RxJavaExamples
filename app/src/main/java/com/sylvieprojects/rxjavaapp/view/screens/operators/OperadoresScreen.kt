package com.sylvieprojects.rxjavaapp.view.screens.operators

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import java.lang.Exception
import java.util.concurrent.TimeUnit

@Composable
fun OperadoresScreen(modifier: Modifier = Modifier) {
    LaunchedEffect(Unit) {
        testLambda()
    }

    Column(modifier = modifier.fillMaxSize()) {

    }
}

private fun testLambda() {
    Log.d("OS", "----------Lambdas--------")
    var disposable: Disposable
    Observable.create<String> { emitter ->
        try {
            emitter.onNext(longDuration())
            emitter.onComplete()
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { d -> disposable = d }
        .subscribe(
            { result -> Log.d("OS", "LongTask onNext: $result") },       // onNext
            { error -> Log.d("OS", "LongTask onError: $error") },        // onError
            { Log.d("OS", "LongTask onComplete") },                      // onComplete
        )
}

private fun testLongTask() {
    Log.d("OS", "----------Long Task--------")
    Observable.create(object : ObservableOnSubscribe<String> {
        override fun subscribe(emitter: ObservableEmitter<String>) {
            try {
                emitter.onNext(longDuration())
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            object : Observer<String>{
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: String) {
                    Log.d("OS", "LongTask onNext: $t")
                }

                override fun onError(e: Throwable) {
                    Log.d("OS", "LongTask onError: $e")
                }

                override fun onComplete() {
                }
            }
        )
}

private fun longDuration(): String {
    try {
        Thread.sleep(10000)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return "Terminado"
}

fun testExceptionCreate() {
    Log.d("OS", "----------Create Exception--------")

    val observable = Observable.create(object : ObservableOnSubscribe<Any> {
        override fun subscribe(emitter: ObservableEmitter<Any>) {
            try {
                emitter.onNext(15 / 3)
                emitter.onNext(3 / 0) // Esto lanza una excepción
            } catch (e: Exception) {
                emitter.onNext(e)
            }
        }
    })

    observable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : Observer<Any> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Any) {
                Log.d("OS", "CreateException onNext: $t")
            }

            override fun onError(e: Throwable) {
                Log.d("OS", "CreateException onError: $e")
            }

            override fun onComplete() {
            }
        })
}


private fun testInterval() {
    Log.d("OS", "----------Interval--------")
    Observable.interval(1, TimeUnit.SECONDS)
        .take(10)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            object : Observer<Long> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Long) {
                    Log.d("OS", "Interval onNext: $t")
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }

            }
        )
}

private fun testCreate() {
    Log.d("OS", "----------Create--------")
    Observable.create(object : ObservableOnSubscribe<String> {
        override fun subscribe(emitter: ObservableEmitter<String>) {
            try {
                emitter.onNext("S")
                emitter.onNext("y")
                emitter.onNext("l")
                emitter.onNext("v")
                emitter.onNext("i")
                emitter.onNext("e")
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {
                Log.d("OS", "Create onNext: $t")
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }

        })
}

private fun testRepeat() {
    Log.d("OS", "----------REPEAT--------")
    Observable.range(1, 3)
        .repeat(3)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Int) {
                Log.d("OS", "Repeat onNext: $t")
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }
        })
}

private fun testRange() {
    Log.d("OS", "----------RANGE--------")
    Observable.range(7, 17)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Int) {
                Log.d("OS", "Range onNext: $t")
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }
        })
}

private fun testFromArray() {
    Log.d("OS", "----------FROM ARRAY--------")
    val numbers = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
    Observable.fromArray(*numbers)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: String) {
                    Log.d("OS", "FromArray onNext: $t")
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }

            }
        )
}

private fun testJustArray() {
    Log.d("OS", "----------JUST ARRAY--------")
    val numbers = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
    Observable.just(numbers)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            object : Observer<Array<String>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Array<String>) {
                    Log.d("OS", "JustArray onNext: ${t.joinToString()}")
                    Log.d("OS", "JustArray onNext size: ${t.size}")
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }

            }
        )
}

private fun probarJust() {
    Log.d("OS", "----------JUST--------")
    Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: String) {
                    Log.d("OS", "Just onNext: $t")
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }

            }
        )
}
