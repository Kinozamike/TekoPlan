package com.kiiplan.tekoplan.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kiiplan.tekoplan.viewmodel.HomeViewModel

@Composable
fun PredictionScreen(
    viewModel: HomeViewModel,
    navController: NavController // tu avais oublié d'importer NavController
) {
    var selected by remember { mutableStateOf("month") }
    var prediction by remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(selected) {
        viewModel.predictRevenue(selected) {
            prediction = it
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Prévision : ${selected.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }}",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer  (Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { selected = "month" }) { Text("Mois") }
            Button(onClick = { selected = "quarter" }) { Text("Trimestre") }
            Button(onClick = { selected = "year" }) { Text("Année") }
        }

        Spacer(Modifier.height(32.dp))

        Text(
            text = "Estimation : ${"%.2f".format(prediction)} €",
            style = MaterialTheme.typography.titleMedium
        )
    }
}
