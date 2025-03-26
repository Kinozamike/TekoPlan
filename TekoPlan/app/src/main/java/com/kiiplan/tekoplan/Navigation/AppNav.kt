package com.kiiplan.tekoplan.navigation

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.tekoplan.billing.BillingClientManager
import com.tekoplan.ui.screens.*
import com.tekoplan.viewmodel.HomeViewModel

@Composable
fun AppNav(viewModel: HomeViewModel, billingManager: BillingClientManager) {
    val navController = rememberNavController()
    val isSubscribed by billingManager.isSubscribed.collectAsState(initial = false)

    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavItem("Accueil", Screen.Home.route, Icons.Default.Home, navController)
                BottomNavItem("Historique", Screen.History.route, Icons.Default.List, navController)
                BottomNavItem("Stats", Screen.Stats.route, Icons.Default.InsertChart, navController)
                BottomNavItem("Prévisions", Screen.Prediction.route, Icons.Default.TrendingUp, navController)
                BottomNavItem("Dashboard", Screen.Dashboard.route, Icons.Default.BarChart, navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onStartPointValidated = { /* à brancher */ },
                    onEndPointValidated = { /* à brancher */ },
                    onTripConfirmed = { viewModel.addTrip(it) },
                    totalToday = viewModel.totalToday.collectAsState().value
                )
            }
            composable(Screen.History.route) {
                val trips = viewModel.tripHistory.collectAsState(initial = emptyList()).value
                HistoryScreen(trips)
            }
            composable(Screen.Stats.route) {
                StatsScreen(viewModel)
            }
            composable(Screen.Prediction.route) {
                PredictionScreen(viewModel)
            }
            composable(Screen.Dashboard.route) {
                if (isSubscribed) {
                    DashboardScreen(viewModel)
                } else {
                    SubscriptionScreen(
                        isSubscribed = false,
                        onSubscribeClick = {
                            billingManager.launchSubscription(activity = LocalContext.current as androidx.activity.ComponentActivity)
                        }
                    )
                }
            }
            composable(Screen.Subscription.route) {
                SubscriptionScreen(
                    isSubscribed = isSubscribed,
                    onSubscribeClick = {
                        billingManager.launchSubscription(activity = LocalContext.current as androidx.activity.ComponentActivity)
                    }
                )
            }
        }
    }
}

@Composable
fun RowScope.BottomNavItem(label: String, route: String, icon: ImageVector, navController: NavHostController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    val selected = currentDestination?.route == route

    BottomNavigationItem(
        selected = selected,
        onClick = { navController.navigate(route) },
        icon = { Icon(icon, contentDescription = label) },
        label = { Text(label) }
    )
}
