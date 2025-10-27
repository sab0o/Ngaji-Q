package com.example.ngajiq.ui.main.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// Data class to represent a teacher
data class Teacher(
    val id: String,
    val name: String,
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
    object OnSearchClick : MapsEvent
}

class MapsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MapsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        // Load dummy data when the ViewModel is created
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
            MapsEvent.OnSearchClick -> {
                // TODO: Implement search logic (e.g., call Google's Geocoding API)
                // For now, we just show the dummy route
                showDummyRoute()
            }
        }
    }

    private fun loadDummyData() {
        val ustKhalid = Teacher("1", "Ust. Khalid", LatLng(-7.9660, 112.6300))
        val ustAhmad = Teacher("2", "Ust. Ahmad", LatLng(-7.9680, 112.6345))

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