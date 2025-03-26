package com.kiiplan.tekoplan.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SubscriptionScreen(
    isSubscribed: Boolean,
    onSubscribeClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center) {
        if (isSubscribed) {
            Text("Merci pour votre abonnement Premium !", style = MaterialTheme.typography.h6)
        } else {
            Text("Abonnez-vous pour débloquer les fonctionnalités avancées.")
            Spacer(Modifier.height(16.dp))
            Button(onClick = onSubscribeClick) {
                Text("S'abonner")
            }
        }
    }
}
