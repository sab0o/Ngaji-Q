package com.example.ngajiq.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ngajiq.R
import androidx.compose.ui.tooling.preview.Preview
import com.example.ngajiq.ui.theme.NgajiQTheme
import androidx.navigation.compose.rememberNavController

@Composable
fun LevelIqra1Screen(navController: NavController) {
    // Daftar huruf hijaiyah Iqra 1
    val hurufList = listOf(
        "Alif", "Ba", "Ta", "Tsa", "Jim", "Ha", "Kho",
        "Dal", "Dzal", "Ra", "Zai", "Sin", "Syin",
        "Shad", "Dhod", "Tho", "Dho", "Ain", "Ghain",
        "Fa", "Qof", "Kaf", "Lam", "Mim", "Nun",
        "Wawu", "Ha'", "Lam Alif", "Hamzah", "Ya'"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFB3E5FC), Color(0xFFE1F5FE)) // gradasi biru muda
                )
            )
    ) {
        // Background pattern (kalau punya gambar background biru seperti contoh)
        Image(
            painter = painterResource(id = R.drawable.bg_iqra), // opsional
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 40.dp, bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Iqra 1",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1)
            )

            Spacer(modifier = Modifier.height(20.dp))

            hurufList.forEachIndexed { index, huruf ->
                val isLeft = index % 2 == 0

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = if (isLeft) Arrangement.Start else Arrangement.End
                ) {
                    LevelButton(
                        text = huruf,
                        onClick = {
                            if (huruf == "Alif") {
                                navController.navigate("flashcardAlif")
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Icon finish di bawah
            Image(
                painter = painterResource(id = R.drawable.ic_finish),
                contentDescription = "Finish",
                modifier = Modifier
                    .size(60.dp)
                    .clickable { /* aksi selesai */ }
            )
        }
    }
}

@Composable
fun LevelButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(90.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.circle_button), // gambar lingkaran putih atau abu
            contentDescription = text,
            modifier = Modifier.size(90.dp)
        )
        Text(
            text = text,
            color = Color(0xFF424242),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewLevelIqra1Screen() {
    NgajiQTheme {
        val navController = rememberNavController()
        LevelIqra1Screen(navController = navController)
    }
}