package com.kiiplan.tekoplan.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tekoplan.data.database.Trip
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HistoryScreen(trips: List<Trip>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Historique des courses", style = MaterialTheme.typography.h6)

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(trips) { trip -> TripItem(trip) }
        }
    }
}

@Composable
fun TripItem(trip: Trip) {
    val date = remember(trip.timestamp) {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        sdf.format(Date(trip.timestamp))
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(Modifier.padding(12.dp)) {
            Text("Date : $date")
            Text("Départ : ${trip.startLat}, ${trip.startLon}")
            Text("Arrivée : ${trip.endLat}, ${trip.endLon}")
            Text("Coût : ${"%.2f".format(trip.cost)} €")
        }
    }
}
