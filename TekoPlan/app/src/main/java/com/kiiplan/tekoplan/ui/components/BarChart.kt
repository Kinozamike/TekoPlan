package com.kiiplan.tekoplan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BarChart(data: Map<String, Double>, goal: Double) {
    val max = maxOf(goal, data.values.maxOrNull() ?: 1.0)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        data.forEach { (day, value) ->
            val heightRatio = (value / max).coerceIn(0.0, 1.0)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .height((heightRatio * 120).dp)
                        .width(16.dp)
                        .background(if (value >= goal) Color(0xFF4CAF50) else Color(0xFF1976D2))
                )
                Spacer(Modifier.height(4.dp))
                Text(day, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}
