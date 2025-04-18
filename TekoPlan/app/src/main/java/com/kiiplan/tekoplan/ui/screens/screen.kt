// Correction de ton fichier `Screen.kt`

package com.kiiplan.tekoplan.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kiiplan.tekoplan.billing.BillingClientManager
import com.kiiplan.tekoplan.viewmodel.HomeViewModel

@Composable
fun Screen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    billingManager: BillingClientManager
) {
    NavHost(
        navController = navController,
        startDestination = "test",
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                navController = navController,
                onTripConfirmed = { /* Action à faire lors de la confirmation d'un trajet */ },
                onStartPointValidated = { /* Action à faire quand le point de départ est validé */ },
                onEndPointValidated = { /* Action à faire quand le point d'arrivée est validé */ }
            )
        }
        composable("dashboard") {
            DashboardScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable("stats") {
            StatsScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable("test") {
            FeatureTestScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}
