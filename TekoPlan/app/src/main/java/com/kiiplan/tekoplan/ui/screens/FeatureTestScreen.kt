package com.kiiplan.tekoplan.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kiiplan.tekoplan.viewmodel.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeatureTestScreen(
    viewModel: HomeViewModel,
    navController: NavHostController
) {
    var costInput by remember { mutableStateOf("") }
    var prediction by remember { mutableDoubleStateOf(0.0) }
    val total by viewModel.totalToday.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Écran de Test") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Test Fonctionnel", style = MaterialTheme.typography.titleLarge)

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = costInput,
                onValueChange = { costInput = it },
                label = { Text("Coût du trajet") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = {
                    val cost = costInput.toDoubleOrNull() ?: 0.0
                    viewModel.addTrip(cost)
                    costInput = ""
                }) {
                    Text("Ajouter trajet")
                }

                Button(onClick = {
                    viewModel.resetDailyTotal()
                }) {
                    Text("Réinitialiser")
                }
            }

            Spacer(Modifier.height(16.dp))
            Text("Total aujourd'hui : ${"%.2f".format(total)} €")

            Spacer(Modifier.height(24.dp))
            Text("Prévisions de revenu :")

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = {
                    viewModel.predictRevenue("month") { prediction = it }
                }) {
                    Text("Mois")
                }
                Button(onClick = {
                    viewModel.predictRevenue("quarter") { prediction = it }
                }) {
                    Text("Trimestre")
                }
                Button(onClick = {
                    viewModel.predictRevenue("year") { prediction = it }
                }) {
                    Text("Année")
                }
            }

            Spacer(Modifier.height(16.dp))
            Text("Estimation : ${"%.2f".format(prediction)} €")
        }
    }
}
