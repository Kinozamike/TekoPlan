package com.kiiplan.tekoplan.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.Alignment

@Composable
fun HomeScreen(
    onStartPointValidated: () -> Unit,
    onEndPointValidated: () -> Unit,
    onTripConfirmed: (Double) -> Unit,
    totalToday: Double
) {
    var tripCost by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Nouvelle course", style = MaterialTheme.typography.h6)

        Button(onClick = onStartPointValidated) {
            Text("Valider le point de départ")
        }

        Button(onClick = onEndPointValidated) {
            Text("Valider le point d'arrivée")
        }

        OutlinedTextField(
            value = tripCost,
            onValueChange = { tripCost = it },
            label = { Text("Coût de la course (€)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                tripCost.text.toDoubleOrNull()?.let(onTripConfirmed)
                tripCost = TextFieldValue("")
            },
            enabled = tripCost.text.toDoubleOrNull() != null
        ) {
            Text("Valider la course")
        }

        Spacer(Modifier.height(32.dp))

        Text("Total aujourd'hui : ${"%.2f".format(totalToday)} €")
    }
}
