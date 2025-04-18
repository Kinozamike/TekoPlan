package com.kiiplan.tekoplan.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem("home", "Accueil", Icons.Filled.Home)
    object Dashboard : BottomNavItem("dashboard", "Tableau", Icons.Filled.Menu)
    object Stats : BottomNavItem("stats", "Statistiques", Icons.Filled.Assessment)
    object Test : BottomNavItem("test", "Test", Icons.Filled.BugReport)

    companion object {
        val items = listOf(Home, Dashboard, Stats, Test)
    }
}
