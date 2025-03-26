package com.kiiplan.tekoplan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kiiplan.tekoplan.ui.theme.TekoPlanTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialisation du client d'abonnement
        val billingManager = BillingClientManager(this)
        billingManager.startConnection()

        setContent {
            TekoPlanTheme {
                val viewModel: HomeViewModel = viewModel()
                AppNav(viewModel = viewModel, billingManager = billingManager)
            }
        }
    }
}
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialisation du client d'abonnement
        val billingManager = BillingClientManager(this)
        billingManager.startConnection()

        setContent {
            TekoPlanTheme {
                val viewModel: HomeViewModel = viewModel()
                AppNav(viewModel = viewModel, billingManager = billingManager)
            }
        }
    }
}