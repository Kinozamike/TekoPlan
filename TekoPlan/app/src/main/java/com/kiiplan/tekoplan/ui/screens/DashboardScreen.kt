package com.kiiplan.tekoplan.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.kiiplan.tekoplan.viewmodel.HomeViewModel
import com.kiiplan.tekoplan.ui.components.BarChart

@Composable
fun DashboardScreen(viewModel: HomeViewModel, navController: NavHostController) {
    var dailyData by remember { mutableStateOf<Map<String, Double>>(emptyMap()) }

    val dailyGoal = 100.0

    LaunchedEffect(Unit) {
        viewModel.getDailyTotals {
            dailyData = it
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Tableau de bord",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(24.dp))
        Text("Performances quotidiennes :")

        if (dailyData.isNotEmpty()) {
            BarChart(data = dailyData, goal = dailyGoal)
        } else {
            Text("Aucune donnée disponible.")
        }

        Spacer(Modifier.height(24.dp))

        val today = dailyData.values.lastOrNull() ?: 0.0
        val message = when {
            today >= dailyGoal -> "Bravo ! Objectif du jour atteint !"
            today >= dailyGoal * 0.75 -> "Tu y es presque, continue !"
            else -> "Courage, chaque course te rapproche de ton objectif !"
        }

        Text(message)
    }
}
