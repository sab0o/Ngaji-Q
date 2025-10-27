package com.example.ngajiq.ui.main.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.ngajiq.R
import com.example.ngajiq.ui.navigation.Routes

@Composable
fun SplashScreen(navController: NavController) {
    // Jalankan efek side (delay + navigate)
    LaunchedEffect(Unit) {
        delay(2000) // Delay 2 detik
        navController.navigate(Routes.MAIN_GRAPH) {
            popUpTo(Routes.SPLASH) { inclusive = true } // Hapus splash dari back stack
        }
    }

    // Tampilan UI Splash
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.alif),
                contentDescription = "App Logo",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "NgajiQ",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
