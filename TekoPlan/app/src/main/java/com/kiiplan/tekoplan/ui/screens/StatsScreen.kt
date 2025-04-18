package com.kiiplan.tekoplan.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kiiplan.tekoplan.data.database.Trip
import com.kiiplan.tekoplan.viewmodel.HomeViewModel

@Composable
fun StatsScreen(viewModel: HomeViewModel, navController: NavHostController) {
    var selected by remember { mutableStateOf("month") }
    var stats by remember { mutableStateOf<List<Trip>>(emptyList()) }

    LaunchedEffect(selected) {
        viewModel.getTripsForPeriod(selected) {
            stats = it
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Bilan ${selected}", style = MaterialTheme.typography.titleLarge)

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { selected = "month" }) { Text("Mois") }
            Button(onClick = { selected = "quarter" }) { Text("Trimestre") }
            Button(onClick = { selected = "year" }) { Text("Année") }
        }

        Spacer(Modifier.height(24.dp))

        val total = stats.sumOf { it.cost }
        val avg = if (stats.isNotEmpty()) total / stats.size else 0.0

        Text("Nombre de courses : ${stats.size}")
        Text("Total des gains : ${"%.2f".format(total)} €")
        Text("Moyenne : ${"%.2f".format(avg)} €")
    }
}
