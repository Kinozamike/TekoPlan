package com.kiiplan.tekoplan.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {

    @Insert
    suspend fun insertTrip(trip: Trip)

    @Query("SELECT * FROM trips ORDER BY timestamp DESC")
    fun getAllTrips(): Flow<List<Trip>>

    @Query("SELECT * FROM trips WHERE timestamp BETWEEN :start AND :end")
    suspend fun getTripsBetween(start: Long, end: Long): List<Trip>

    @Query("DELETE FROM trips")
    suspend fun clearAllTrips()
}
