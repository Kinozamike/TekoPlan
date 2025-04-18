package com.kiiplan.tekoplan.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kiiplan.tekoplan.viewmodel.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavController,
    onTripConfirmed: () -> Unit,
    onStartPointValidated: () -> Unit,
    onEndPointValidated: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Accueil") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text("Bienvenue sur l'écran d'accueil", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { onStartPointValidated() }, modifier = Modifier.fillMaxWidth()) {
                Text("Valider le point de départ")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { onEndPointValidated() }, modifier = Modifier.fillMaxWidth()) {
                Text("Valider le point d'arrivée")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { onTripConfirmed() }, modifier = Modifier.fillMaxWidth()) {
                Text("Confirmer la course")
            }
        }
    }
}
