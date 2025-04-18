package com.kiiplan.tekoplan.Navigation

import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kiiplan.tekoplan.billing.BillingClientManager
import com.kiiplan.tekoplan.ui.screens.*
import com.kiiplan.tekoplan.viewmodel.HomeViewModel

@Composable
fun AppNav(
    viewModel: HomeViewModel,
    billingManager: BillingClientManager
) {
    val navController = rememberNavController()
    val bottomNavItems = BottomNavItem.items

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry = navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry.value?.destination

                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentDestination?.route == item.route,
                        onClick = { navController.navigate(item.route) },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { paddingValues ->
        Screen(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            viewModel = viewModel,
            billingManager = billingManager
        )
    }
}
