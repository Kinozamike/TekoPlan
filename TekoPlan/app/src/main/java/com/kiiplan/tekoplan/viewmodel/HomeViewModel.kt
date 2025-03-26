package com.kiiplan.tekoplan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tekoplan.data.database.AppDatabase
import com.tekoplan.data.database.Trip
import com.tekoplan.utils.DateUtils
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val tripDao = AppDatabase.getDatabase(application).tripDao()

    private val _totalToday = MutableStateFlow(0.0)
    val totalToday: StateFlow<Double> = _totalToday

    private var startPoint: Pair<Double, Double>? = null
    private var endPoint: Pair<Double, Double>? = null

    val tripHistory: Flow<List<Trip>> = tripDao.getAllTrips()

    init {
        // Initialise le total journalier en fonction des données du jour
        calculateTodayTotal()
    }

    private fun calculateTodayTotal() {
        viewModelScope.launch {
            val today = DateUtils.startOfToday()
            val now = System.currentTimeMillis()
            val trips = tripDao.getTripsBetween(today, now)
            _totalToday.value = trips.sumOf { it.cost }
        }
    }

    fun setStartPoint(lat: Double, lon: Double) {
        startPoint = lat to lon
    }

    fun setEndPoint(lat: Double, lon: Double) {
        endPoint = lat to lon
    }

    fun addTrip(cost: Double) {
        val start = startPoint
        val end = endPoint

        if (start != null && end != null && cost > 0.0) {
            val trip = Trip(
                startLat = start.first,
                startLon = start.second,
                endLat = end.first,
                endLon = end.second,
                cost = cost,
                timestamp = System.currentTimeMillis()
            )

            viewModelScope.launch {
                tripDao.insertTrip(trip)
                _totalToday.value += cost
            }

            startPoint = null
            endPoint = null
        }
    }

    fun resetDailyTotal() {
        viewModelScope.launch {
            tripDao.clearAllTrips()
            _totalToday.value = 0.0
        }
    }

    fun getTripsForPeriod(period: String, onResult: (List<Trip>) -> Unit) {
        val (start, end) = when (period) {
            "month" -> DateUtils.startOfCurrentMonth() to DateUtils.now()
            "quarter" -> DateUtils.startOfCurrentQuarter() to DateUtils.now()
            "year" -> DateUtils.startOfCurrentYear() to DateUtils.now()
            else -> 0L to 0L
        }

        viewModelScope.launch {
            val trips = tripDao.getTripsBetween(start, end)
            onResult(trips)
        }
    }

    fun predictRevenue(period: String, onResult: (Double) -> Unit) {
        viewModelScope.launch {
            val allTrips = tripDao.getAllTrips().firstOrNull() ?: emptyList()

            if (allTrips.isEmpty()) {
                onResult(0.0)
                return@launch
            }

            val days = allTrips.map { it.timestamp / (1000 * 60 * 60 * 24) }.toSet().size
            val total = allTrips.sumOf { it.cost }
            val dailyAverage = if (days > 0) total / days else 0.0

            val projection = when (period) {
                "month" -> dailyAverage * 30
                "quarter" -> dailyAverage * 90
                "year" -> dailyAverage * 365
                else -> 0.0
            }

            onResult(projection)
        }
    }

    fun getDailyTotals(onResult: (Map<String, Double>) -> Unit) {
        viewModelScope.launch {
            val trips = tripDao.getAllTrips().firstOrNull() ?: emptyList()
            val grouped = trips.groupBy {
                val cal = Calendar.getInstance()
                cal.timeInMillis = it.timestamp
                "${cal.get(Calendar.DAY_OF_MONTH)}/${cal.get(Calendar.MONTH) + 1}"
            }
            val totals = grouped.mapValues { (_, list) -> list.sumOf { it.cost } }
            onResult(totals)
        }
    }
}
