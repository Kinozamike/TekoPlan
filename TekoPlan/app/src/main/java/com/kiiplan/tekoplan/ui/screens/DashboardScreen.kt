package com.kiiplan.tekoplan.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tekoplan.viewmodel.HomeViewModel
import com.tekoplan.ui.components.BarChart

@Composable
fun DashboardScreen(viewModel: HomeViewModel) {
    var dailyData by remember { mutableStateOf<Map<String, Double>>(emptyMap()) }

    // Objectif journalier pour motiver l'utilisateur
    val dailyGoal = 100.0

    LaunchedEffect(Unit) {
        viewModel.getDailyTotals {
            dailyData = it
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Tableau de bord", style = MaterialTheme.typography.h6)

        Spacer(Modifier.height(24.dp))
        Text("Performances quotidiennes :")

        if (dailyData.isNotEmpty()) {
            BarChart(data = dailyData, goal = dailyGoal)
        } else {
            Text("Aucune donnée disponible.")
        }

        Spacer(Modifier.height(24.dp))

        // Message de motivation
        val today = dailyData.values.lastOrNull() ?: 0.0
        val message = when {
            today >= dailyGoal -> "Bravo ! Objectif du jour atteint !"
            today >= dailyGoal * 0.75 -> "Tu y es presque, continue !"
            else -> "Courage, chaque course te rapproche de ton objectif !"
        }

        Text(message)
    }
}
