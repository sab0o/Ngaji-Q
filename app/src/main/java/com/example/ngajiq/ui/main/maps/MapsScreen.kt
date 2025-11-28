package com.example.ngajiq.ui.main.maps

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.CameraUpdateFactory

// Main Entry Point (Stateful)
@Composable
fun MapsScreen(
    mapsViewModel: MapsViewModel = viewModel()
) {
    val uiState by mapsViewModel.uiState.collectAsState()
    MapsScreenContent(
        uiState = uiState,
        onEvent = mapsViewModel::onEvent
    )
}

// UI Composable (Stateless)
@Composable
fun MapsScreenContent(
    uiState: MapsUiState,
    onEvent: (MapsEvent) -> Unit
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(uiState.userLocation, 15f)
    }

    LaunchedEffect(uiState.searchedLocation) {
        uiState.searchedLocation?.let {
            // Animasikan kamera ke posisi baru
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(it, 15f),
                durationMs = 1000 // durasi animasi 1 detik
            )
        }
    }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // --- 1. PETA (LAPISAN PALING BAWAH) ---
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings = MapUiSettings(zoomControlsEnabled = false, myLocationButtonEnabled = false),
                onMapClick = { onEvent(MapsEvent.OnMapClick) }
            ) {

            }

            // --- 2. HEADER GRADIENT DAN KONTENNYA ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF74D5FF), // Warna biru muda
                                Color(0xFF5696F5)  // Warna biru lebih gelap
                            )
                        )
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp)) // Spacer untuk status bar
                Text(
                    text = "Cari Guru Ngaji",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                SearchBar(onSearchClick = { /*TODO*/ })
            }


            // --- 3. KARTU INFO GURU (LAPISAN PALING ATAS) ---
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
    onSearchClick: (String) -> Unit
    ) {
    var searchQuery by remember { mutableStateOf("") }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Search Field
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Ketik lokasi", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon", tint = Color.Gray) },
            shape = CircleShape,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.weight(1f)
        )

        Spacer(Modifier.width(8.dp))

        // Tombol "Cari"
        Button(
            onClick = { onSearchClick(searchQuery) },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF45D1FF) // Warna cyan/biru muda
            ),
            modifier = Modifier.height(56.dp), // Samakan tinggi dengan TextField
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            Text("Cari", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun TeacherInfoCard(
    modifier: Modifier = Modifier,
    teacher: Teacher
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Foto Profil
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF9FF29A)) // Warna background hijau muda
            ) {
                // TODO: Ganti dengan gambar asli menggunakan Coil
                Image(
                    imageVector = Icons.Default.AccountCircle, // Placeholder
                    contentDescription = teacher.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }

            Spacer(Modifier.width(16.dp))

            // Kolom Info Teks dan Tombol
            Column {
                Text(teacher.name, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                Spacer(Modifier.height(4.dp))
                Text(teacher.address, fontSize = 12.sp, color = Color.Gray, lineHeight = 16.sp)
                Spacer(Modifier.height(12.dp))

                // Tombol Kontak
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ContactButton(
                        text = teacher.phone,
                        icon = Icons.Default.Call,
                        onClick = { /*TODO: Handle Call*/ }
                    )
                    ContactButton(
                        text = teacher.whatsapp,
                        icon = Icons.Default.Chat, // Icon mirip WhatsApp
                        onClick = { /*TODO: Handle WhatsApp*/ }
                    )
                }
            }
        }
    }
}

@Composable
private fun ContactButton(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFE0F7FF), // Warna biru sangat muda
            contentColor = Color(0xFF45D1FF)    // Warna icon & teks biru
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(4.dp))
            Text(text, fontSize = 12.sp)
        }
    }
}


// =================================================================
// PREVIEW
// =================================================================
@Preview(showBackground = true, name = "Preview Peta dengan Info Card")
@Composable
fun MapsScreenWithCardPreview() {
    val fakeTeacher = Teacher(
        id = "preview_1",
        name = "Ust. Khalid",
        address = "Jalan Kalimantan No. 12, Kaliwates, Sumbersari, Jember, Jawa Timur 68121",
        phone = "081xxxx...",
        whatsapp = "081xxxx...",
        location = LatLng(-6.2088, 106.8456)
    )
    val fakeUiState = MapsUiState(selectedTeacher = fakeTeacher)

    MapsScreenContent(
        uiState = fakeUiState,
        onEvent = {}
    )
}

@Preview(showBackground = true, name = "Preview Peta Tampilan Awal")
@Composable
fun MapsScreenDefaultPreview() {
    val fakeUiState = MapsUiState(selectedTeacher = null)

    MapsScreenContent(
        uiState = fakeUiState,
        onEvent = {}
    )
}