package com.example.ngajiq.ui.main.iqra

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.ngajiq.data.repository.IqraRepository
import com.example.ngajiq.ui.main.iqra.components.IqraCard
import com.example.ngajiq.ui.navigation.Routes
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
            Spacer(modifier = Modifier.height(34.dp))
            LazyColumn(modifier = Modifier
                .padding(16.dp)
            ) {
                items(IqraRepository.iqras) { iqra ->
                    IqraCard(
                        iqraNumber = iqra.id,
                        title = iqra.title,
                        currentLevel = iqra.currentLevel,
                        totalLevel = 30,
                        imageRes = R.drawable.iqra,
                        lockedState = iqra.lockedState,
                        onClick = {
                            navController.navigate("${Routes.MAPIQRA}/${iqra.id}")
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
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
