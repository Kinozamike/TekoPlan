package com.kiiplan.tekoplan.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

// Représente un trajet enregistré dans la base locale
@Entity(tableName = "trips")
data class Trip(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val startLat: Double,
    val startLon: Double,
    val endLat: Double,
    val endLon: Double,
    val cost: Double,
    val timestamp: Long
)
