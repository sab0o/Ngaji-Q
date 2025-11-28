package com.example.ngajiq.ui.main.iqra

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ngajiq.R
import com.example.ngajiq.ui.main.iqra.components.ButtonBlue
import com.example.ngajiq.ui.main.iqra.components.ButtonReview
import com.example.ngajiq.ui.main.iqra.components.PercentageCircle
import com.example.ngajiq.ui.main.iqra.components.ScoreBox
import com.example.ngajiq.ui.main.iqra.components.StarRating
import com.example.ngajiq.ui.navigation.Routes
import androidx.navigation.compose.rememberNavController



@Composable
fun LearningCompletionScreen(
    navController: NavController,
    correct: Int = 1,
    incorrect: Int = 0,
    onReview: () -> Unit = {}
) {
    // STATE untuk menentukan mode tampilan
    var showCongrats by remember { mutableStateOf(false) }

    if (showCongrats) {
        CongratsScreen(
            percentage = if (correct + incorrect == 0) 0
            else ((correct.toFloat() / (correct + incorrect)) * 100).toInt(),
            navController = navController
        )
    } else {
        ResultScreen(
            correct = correct,
            incorrect = incorrect,
            onNext = { showCongrats = true },
            onReview = onReview
        )
    }
}

@Composable
fun ResultScreen(
    correct: Int,
    incorrect: Int,
    onNext: () -> Unit,
    onReview: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Confetti + Title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.confetti),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillWidth
            )

            Text(
                text = "Hasil Belajar",
                color = Color(0xFF353535),
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(top = 210.dp)
            )
        }

        PercentageCircle(
            percentage = if (correct + incorrect == 0) 0
            else ((correct.toFloat() / (correct + incorrect)) * 100).toInt()
        )

        Spacer(modifier = Modifier.height(48.dp))

        ScoreBox(correct = correct, incorrect = incorrect)

        Spacer(modifier = Modifier.height(44.dp))

        ButtonBlue(
            text = "Lanjut",
            onClick = onNext,
            buttonColor = Color(0xFF55D5FF),
            shadowColor = Color(0xFF42BBE4),
            textColor = Color.White,
            modifier = Modifier.width(242.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonReview(
            text = "Review Jawaban",
            onClick = onReview,
            buttonColor = Color.White,
            shadowColor = Color(0XFFD2E7FF),
            textColor = Color.Black,
            modifier = Modifier.width(242.dp)
        )
    }
}

@Composable
fun CongratsScreen(percentage: Int,navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Confetti top
        Image(
            painter = painterResource(id = R.drawable.confetti),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = "Selamat!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF353535)
        )

        Spacer(modifier = Modifier.height(20.dp))

        StarRating(percentage = percentage)

        Spacer(modifier = Modifier.height(50.dp))

        Box(
            modifier = Modifier
                .background(Color(0xFFBFEAFF), shape = androidx.compose.foundation.shape.RoundedCornerShape(50))
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Text(
                text = "+50 Poin",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Bagus! Kamu berhasil\nmenyelesaikan pembelajaran ini",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color(0xFF353535),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(50.dp))

        ButtonBlue(
            text = "SELESAI",
            onClick = {
                navController.navigate(Routes.HOME);
            },
            buttonColor = Color(0xFF55D5FF),
            shadowColor = Color(0xFF42BBE4),
            textColor = Color.White,
            modifier = Modifier.width(242.dp)
        )
    }
}

@Preview(showBackground = true, name = "Hasil Belajar")
@Composable
fun PreviewLearningCompletion() {
    LearningCompletionScreen(
        navController = rememberNavController(),
        correct = 3,
        incorrect = 1
    )
}

@Preview(showBackground = true, name = "Congrats Screen")
@Composable
fun PreviewCongratsScreen() {
    CongratsScreen(
        percentage = 80,
        navController = rememberNavController()
    )
}
