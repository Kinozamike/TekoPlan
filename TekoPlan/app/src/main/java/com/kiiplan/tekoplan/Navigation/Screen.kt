package com.tekoplan.navigation

// Liste des routes de navigation dans l'application
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object History : Screen("history")
    object Stats : Screen("stats")
    object Prediction : Screen("prediction")
    object Dashboard : Screen("dashboard")
    object Subscription : Screen("subscription")
}
