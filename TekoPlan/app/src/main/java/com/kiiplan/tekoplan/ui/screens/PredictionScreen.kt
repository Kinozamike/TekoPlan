package com.kiiplan.tekoplan.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tekoplan.viewmodel.HomeViewModel

@Composable
fun PredictionScreen(viewModel: HomeViewModel) {
    var selected by remember { mutableStateOf("month") }
    var prediction by remember { mutableStateOf(0.0) }

    LaunchedEffect(selected) {
        viewModel.predictRevenue(selected) {
            prediction = it
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Prévision ${selected}", style = MaterialTheme.typography.h6)

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { selected = "month" }) { Text("Mois") }
            Button(onClick = { selected = "quarter" }) { Text("Trimestre") }
            Button(onClick = { selected = "year" }) { Text("Année") }
        }

        Spacer(Modifier.height(24.dp))
        Text("Estimation : ${"%.2f".format(prediction)} €")
    }
}
