package com.sylvieprojects.rxjavaapp.view.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sylvieprojects.rxjavaapp.view.screens.CompositeDisposableScreen
import com.sylvieprojects.rxjavaapp.view.screens.DisposableScreen
import com.sylvieprojects.rxjavaapp.view.screens.IntroScreen
import com.sylvieprojects.rxjavaapp.view.screens.operators.OperadoresScreen
import com.sylvieprojects.rxjavaapp.view.screens.PrincipalScreen
import com.sylvieprojects.rxjavaapp.view.screens.backPressure.BackPressureScreen
import com.sylvieprojects.rxjavaapp.view.screens.coldAndHot.ColdAndHotScreen
import com.sylvieprojects.rxjavaapp.view.screens.operators.sectionFive.OperadoresMOSOScreen
import com.sylvieprojects.rxjavaapp.view.screens.operators.OperadoresSEIOScreen
import com.sylvieprojects.rxjavaapp.view.screens.operators.OperadoresTOScreen
import com.sylvieprojects.rxjavaapp.view.screens.operators.sectionFive.OperadoresBOCOScreen
import com.sylvieprojects.rxjavaapp.view.screens.rxBus.RxBusScreen
import com.sylvieprojects.rxjavaapp.view.screens.subjects.SubjectsScreen
import com.sylvieprojects.rxjavaapp.view.screens.typeObservables.TypeObservablesScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Principal) {
        composable<Principal> {
            PrincipalScreen(
                navigateToIntro = {
                    navController.navigate(Intro)
                }, navigateToDisposable = {
                    navController.navigate(Disposable)
                }, navigateToCompositeDispose = {
                    navController.navigate(CompositeDisposable)
                }, navigateToOperadores = {
                    navController.navigate(Operadores)
                }, navigateToOperadoresTO = {
                    navController.navigate(OperadoresTransObservables)
                }, navigateToOperadoresSEIO = {
                    navController.navigate(OperadoresSEIO)
                }, navigateToOperadoresMOSO = {
                    navController.navigate(OperadoresMOSO)
                }, navigateToOperadoresBOCO = {
                    navController.navigate(OperadoresBOCO)
                }, navigateTypeObservables = {
                    navController.navigate(TypeObservables)
                }, navigateToSubjects = {
                    navController.navigate(Subjects)
                }, navigateToRxBus = {
                    navController.navigate(RxBus)
                }, navigateToBackPressure = {
                    navController.navigate(BackPressure)
                }, navigateToColdAndHot = {
                    navController.navigate(ColdAndHot)
                }
            )
        }
        composable<Intro> { IntroScreen() }
        composable<Disposable> { DisposableScreen() }
        composable<CompositeDisposable> { CompositeDisposableScreen() }
        composable<Operadores> { OperadoresScreen() }
        composable<OperadoresTransObservables> { OperadoresTOScreen() }
        composable<OperadoresSEIO> { OperadoresSEIOScreen() }
        composable<OperadoresMOSO> { OperadoresMOSOScreen() }
        composable<OperadoresBOCO> { OperadoresBOCOScreen() }
        composable<TypeObservables> { TypeObservablesScreen() }
        composable<Subjects> { SubjectsScreen() }
        composable<RxBus> { RxBusScreen() }
        composable<BackPressure> { BackPressureScreen() }
        composable<ColdAndHot> { ColdAndHotScreen() }
    }
}