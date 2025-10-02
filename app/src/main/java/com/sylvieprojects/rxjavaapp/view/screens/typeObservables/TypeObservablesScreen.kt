package com.sylvieprojects.rxjavaapp.view.screens.typeObservables

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.sylvieprojects.rxjavaapp.view.screens.operators.Empleado
import com.sylvieprojects.rxjavaapp.view.screens.operators.EmpleadoDummyData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableEmitter
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.CompletableOnSubscribe
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.MaybeEmitter
import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.core.MaybeOnSubscribe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleEmitter
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.core.SingleOnSubscribe
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction

@Composable
fun TypeObservablesScreen(modifier: Modifier = Modifier) {
    LaunchedEffect(Unit) {
        testFlowable()
    }
}

private fun testFlowable() {
    Log.d("Observables", "----------Flowable------------")
    val flowable = Flowable.range(1, 10_000)
    val observer = object : MaybeObserver<Int> {
        override fun onSubscribe(d: Disposable) {
        }

        override fun onSuccess(t: Int) {
            Log.d("Observables", "onSuccess: $t")
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
        }
    }
    flowable.reduce(object : BiFunction<Int, Int, Int> {
        override fun apply(t1: Int, t2: Int): Int {
            return t1 + t2
        }
    }).subscribe(observer)
}

private fun testCompletable() {
    Log.d("Observables", "----------Completable------------")
    val completable = Completable.create(object : CompletableOnSubscribe {
        override fun subscribe(emitter: CompletableEmitter) {
            emitter.onComplete()
        }
    })
    val completableObserver: CompletableObserver = object : CompletableObserver {
        override fun onSubscribe(d: Disposable) {
        }

        override fun onComplete() {
            Log.d("Observables", "onComplete Completable")
        }

        override fun onError(e: Throwable) {
        }
    }
    completable.subscribe(completableObserver)
}

private fun testMaybe() {
    Log.d("Observables", "----------Maybe------------")
    val empleadoMaybe = Maybe.create(object : MaybeOnSubscribe<Empleado> {
        override fun subscribe(emitter: MaybeEmitter<Empleado>) {
            emitter.onSuccess(EmpleadoDummyData.empleado)
        }
    })
    val empleadoMaybeEmpty = Maybe.empty<Empleado>()
    val maybeObserver: MaybeObserver<Empleado> = object : MaybeObserver<Empleado> {
        override fun onSubscribe(d: Disposable) {
        }

        override fun onSuccess(t: Empleado) {
            Log.d("Observables", "onSuccess Maybe: ${t.name}")
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
            Log.d("Observables", "onComplete Maybe")
        }
    }
//    empleadoMaybe.subscribe(maybeObserver)
    empleadoMaybeEmpty.subscribe(maybeObserver)
}

private fun testSingle() {
    Log.d("Observables", "----------Single------------")
    val empleadoSingle = Single.create(object : SingleOnSubscribe<Empleado> {
        override fun subscribe(emitter: SingleEmitter<Empleado>) {
            emitter.onSuccess(EmpleadoDummyData.empleado)
        }
    })
    val singleObserver: SingleObserver<Empleado> = object : SingleObserver<Empleado> {
        override fun onSubscribe(d: Disposable) {
        }

        override fun onSuccess(t: Empleado) {
            Log.d("Observables", "onSuccess Single: ${t.name}")
        }

        override fun onError(e: Throwable) {
        }
    }
    empleadoSingle.subscribe(singleObserver)
}

private fun testObservable() {
    Log.d("Observables", "----------Observable------------")
    val empleados = EmpleadoDummyData.listEmpleados
    val empleadoObservable = Observable.create<Empleado> { emitter ->
        for (empleado in empleados) {
            emitter.onNext(empleado)
        }
        emitter.onComplete()
    }
    val observerEmpleado: Observer<Empleado> = object : Observer<Empleado> {
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: Empleado) {
            Log.d("Observables", "onNext Observable: ${t.name}")
        }

        override fun onError(e: Throwable) {
        }

        override fun onComplete() {
        }
    }
    empleadoObservable.subscribe(observerEmpleado)
}
