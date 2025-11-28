package com.example.ngajiq.ui.main.maps

import android.app.Application
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
data class Teacher(
    val id: String,
    val name: String,
    val address: String,
    val phone: String,
    val whatsapp: String,
    val location: LatLng
)

// Data class to hold all the information for your UI
data class MapsUiState(
    val userLocation: LatLng = LatLng(-7.9666, 112.6326), // Dummy: Alun-Alun Malang
    val searchedLocation: LatLng? = LatLng(-7.9546, 112.6390), // Dummy: Museum Brawijaya
    val teachers: List<Teacher> = emptyList(),
    val selectedTeacher: Teacher? = null,
    val routePoints: List<LatLng> = emptyList() // For the blue route line
)

// Events that the UI can send to the ViewModel
sealed interface MapsEvent {
    data class OnTeacherMarkerClick(val teacher: Teacher) : MapsEvent
    object OnMapClick : MapsEvent
    data class OnSearchClick(val query: String) : MapsEvent
}

class MapsViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(MapsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadDummyData()
    }

    fun onEvent(event: MapsEvent) {
        when (event) {
            is MapsEvent.OnTeacherMarkerClick -> {
                _uiState.update { it.copy(selectedTeacher = event.teacher) }
            }
            MapsEvent.OnMapClick -> {
                // Hide the info window when tapping on the map
                _uiState.update { it.copy(selectedTeacher = null) }
            }
            is MapsEvent.OnSearchClick -> {
                geocodeAndSearch(event.query)
            }
        }
    }

    private fun geocodeAndSearch(query: String) {
        // Jalankan di thread IO karena ini adalah operasi jaringan/disk
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Dapatkan context dari application
                val geocoder = Geocoder(getApplication(), java.util.Locale.getDefault())
                // Ambil 1 hasil terbaik dari nama lokasi
                val results = geocoder.getFromLocationName(query, 1)

                if (results != null && results.isNotEmpty()) {
                    val address = results[0]
                    val newLocation = LatLng(address.latitude, address.longitude)

                    // Update UI State dengan lokasi baru dan rute baru
                    _uiState.update { currentState ->
                        currentState.copy(
                            searchedLocation = newLocation,
                            // Buat rute sederhana dari lokasi user ke lokasi baru
                            routePoints = listOf(currentState.userLocation, newLocation)
                        )
                    }
                } else {
                    Log.w("MapsViewModel", "Geocoding failed: No results for query '$query'")
                    // TODO: Tampilkan pesan error ke pengguna (misal: via Toast atau Snackbar)
                }
            } catch (e: IOException) {
                Log.e("MapsViewModel", "Geocoding failed for query '$query'", e)
                // TODO: Tampilkan pesan error ke pengguna
            }
        }
    }

    private fun loadDummyData() {
        // FIX: Provide all required arguments for the Teacher data class
        val ustKhalid = Teacher(
            id = "1",
            name = "Ust. Khalid",
            address = "Masjid Agung Jami', Malang", // Example address
            phone = "081234567890", // Example phone
            whatsapp = "081234567890", // Example WhatsApp
            location = LatLng(-7.9660, 112.6300)
        )
        val ustAhmad = Teacher(
            id = "2",
            name = "Ust. Ahmad",
            address = "Masjid Sabilillah, Malang", // Example address
            phone = "089876543210", // Example phone
            whatsapp = "089876543210", // Example WhatsApp
            location = LatLng(-7.9680, 112.6345)
        )

        _uiState.update {
            it.copy(
                teachers = listOf(ustKhalid, ustAhmad)
            )
        }
    }
    private fun showDummyRoute() {
        // This simulates the route from your green marker to the red pin
        _uiState.update {
            it.copy(
                routePoints = listOf(
                    it.userLocation,
                    LatLng(-7.9600, 112.6350), // An intermediate point
                    it.searchedLocation!!
                )
            )
        }
    }
}