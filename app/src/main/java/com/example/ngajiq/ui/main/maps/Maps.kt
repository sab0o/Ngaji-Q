package com.example.ngajiq.ui.main.maps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

// Main Entry Point
@Composable
fun MapsScreen(
    mapsViewModel: MapsViewModel = viewModel()
) {
    val uiState by mapsViewModel.uiState.collectAsState()

    // This controls the map's camera
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(uiState.userLocation, 15f)
    }

    Scaffold(
        // ...
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            val topBackgroundHeight = 200.dp

            // --- 1. DRAW THE MAP FIRST (BOTTOM LAYER) ---
            GoogleMap(
                modifier = Modifier.fillMaxSize(), // This fills the whole screen
                cameraPositionState = cameraPositionState,
                properties = MapProperties(
                    isMyLocationEnabled = false // We use a custom marker
                ),
                uiSettings = MapUiSettings(
                    zoomControlsEnabled = false,
                    myLocationButtonEnabled = false
                ),
                onMapClick = {
                    mapsViewModel.onEvent(MapsEvent.OnMapClick)
                }
            ) {
                // ... All your Markers and Polyline code ...
            }

            // --- 2. DRAW THE GRADIENT (ON TOP OF MAP) ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(topBackgroundHeight)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF86D1FF),
                                Color(0xFF5696F5)
                            )
                        )
                    )
            )

            // --- 3. DRAW THE TEXT (ON TOP OF GRADIENT) ---
            Text(
                text = "Cari Guru Ngaji",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp)
            )

            // --- 4. DRAW THE SEARCH BAR (ON TOP OF GRADIENT) ---
            SearchBar(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    // Combine padding into one modifier for clarity
                    .padding(
                        top = topBackgroundHeight / 2,
                        start = 16.dp,
                        end = 16.dp
                    ),
                onSearchClick = {
                    mapsViewModel.onEvent(MapsEvent.OnSearchClick)
                }
            )

            // --- 5. DRAW THE INFO CARD (ON TOP OF MAP) ---
            uiState.selectedTeacher?.let { teacher ->
                TeacherInfoCard(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 16.dp, vertical = 24.dp),
                    teacher = teacher
                )
            }
        }
    }
}

@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        placeholder = { Text("Ketik lokasi") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
        trailingIcon = {
            Button(
                onClick = onSearchClick,
                shape = RoundedCornerShape(20.dp), // Your corner radius
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF70B6FA) // Your blue
                ),
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text("Cari")
            }
        },
        shape = RoundedCornerShape(20.dp), // Your corner radius
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
    )
}

@Composable
private fun TeacherInfoCard(
    modifier: Modifier = Modifier,
    teacher: Teacher
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp), // Your corner radius
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TODO: Replace with real image (e.g., using Coil library)
            Image(
                // Using a placeholder icon
                imageVector = Icons.Default.AccountCircle,
                contentDescription = teacher.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )

            Spacer(Modifier.width(16.dp))

            Column(Modifier.weight(1f)) {
                Text(teacher.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("Guru Ngaji", fontSize = 14.sp, color = Color.Gray)
            }

            Spacer(Modifier.width(16.dp))

            // "Chat" and "Arahkan" buttons
            Row {
                Button(
                    onClick = { /*TODO: Handle Chat*/ },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF70B6FA).copy(alpha = 0.2f),
                        contentColor = Color(0xFF5696F5)
                    ),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    Text("Chat")
                }
                Spacer(Modifier.width(8.dp))
                Button(
                    onClick = { /*TODO: Handle Route*/ },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF70B6FA)
                    ),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    Text("Arahkan")
                }
            }
        }
    }
}
