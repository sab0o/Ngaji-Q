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
import com.example.ngajiq.ui.main.iqra.MateriCard
import com.example.ngajiq.ui.theme.NgajiQTheme

@Composable
fun MateriScreen(selectedItem: Int, navController: NavController) {
    var selectedItem by remember { mutableIntStateOf(1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF86D1FF), Color.White),
                        startY = 0f,
                        endY = 1000f
                    )
                )
        )

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

                MateriCard(
                    title = "Iqra 1",
                    desc = "Mengenal huruf hijaiyah",
                    progress = 3,
                    isActive = true,
                    onClick = { navController.navigate("levelIqra1") } // Navigasi ke layar Iqra 1
                )
                Spacer(modifier = Modifier.height(12.dp))
                MateriCard("Iqra 2", "Belajar membaca kata", 0)
                Spacer(modifier = Modifier.height(12.dp))
                MateriCard("Iqra 3", "Belajar mad thabi’i", 0)
                Spacer(modifier = Modifier.height(12.dp))
                MateriCard("Iqra 4", "Mengenal tanda bacaan", 0)
                Spacer(modifier = Modifier.height(12.dp))
                MateriCard("Iqra 5", "Belajar Waqaf dan Qalqalah", 0)
                Spacer(modifier = Modifier.height(12.dp))
                MateriCard("Iqra 6", "Memahami Tajwid", 0)
            }
        }

        // Header atas
        Text(
            text = "Belajar Iqra",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(start = 37.dp, top = 65.dp, end = 24.dp)
                .align(Alignment.TopStart)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun MateriPreview() {
    NgajiQTheme {
        MateriScreen(selectedItem = 1, navController = rememberNavController())
    }
}
