package com.example.semana01.nav

sealed class NavRouter(val route: String) {
    object LoginScreen : NavRouter("loginScreen")
    object RegisterScreen : NavRouter("registerScreen")
    object RecoverScreen : NavRouter("recoverScreen")
    object HomeScreen : NavRouter("homeScreen")
    object RecetaScreen : NavRouter("recetaScreen")

}