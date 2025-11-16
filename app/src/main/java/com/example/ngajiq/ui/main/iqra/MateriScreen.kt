package com.example.ngajiq.ui.main.iqra

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ngajiq.R
import com.example.ngajiq.ui.main.iqra.components.IqraCard
import com.example.ngajiq.ui.theme.NgajiQTheme

@Composable
fun MateriScreen(selectedItem: Int, navController: NavController) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Background gradient
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF5696F5), Color.White),
                        startY = 0f,
                        endY = 1000f
                    )
                )
        )

        // White card container
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            color = Color.White,
            shadowElevation = 4.dp
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Spacer(modifier = Modifier.height(34.dp))

                // Iqra 1
                IqraCard(
                    iqraNumber = 1,
                    title = "Mengenal huruf hijaiyah",
                    currentLevel = 1,
                    totalLevel = 30,
                    imageRes = R.drawable.iqra,   // sesuaikan nama file
                    onClick = { navController.navigate("levelIqra1") }
                )

                Spacer(modifier = Modifier.height(12.dp))

                IqraCard(
                    iqraNumber = 2,
                    title = "Belajar membaca kata",
                    currentLevel = 0,
                    totalLevel = 30,
                    imageRes = R.drawable.iqra
                )

                Spacer(modifier = Modifier.height(12.dp))

                IqraCard(
                    iqraNumber = 3,
                    title = "Belajar mad thabi’i",
                    currentLevel = 0,
                    totalLevel = 30,
                    imageRes = R.drawable.iqra
                )

                Spacer(modifier = Modifier.height(12.dp))

                IqraCard(
                    iqraNumber = 4,
                    title = "Mengenal tanda bacaan",
                    currentLevel = 0,
                    totalLevel = 30,
                    imageRes = R.drawable.iqra
                )

                Spacer(modifier = Modifier.height(12.dp))

                IqraCard(
                    iqraNumber = 5,
                    title = "Belajar Waqaf dan Qalqalah",
                    currentLevel = 0,
                    totalLevel = 30,
                    imageRes = R.drawable.iqra
                )

                Spacer(modifier = Modifier.height(12.dp))

                IqraCard(
                    iqraNumber = 6,
                    title = "Memahami Tajwid",
                    currentLevel = 0,
                    totalLevel = 30,
                    imageRes = R.drawable.iqra
                )
            }
        }

        // Header
        Text(
            text = "Belajar Iqra",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(start = 37.dp, top = 49.dp)
                .align(Alignment.TopStart)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MateriPreview() {
    NgajiQTheme {
        MateriScreen(1, navController = rememberNavController())
    }
}
