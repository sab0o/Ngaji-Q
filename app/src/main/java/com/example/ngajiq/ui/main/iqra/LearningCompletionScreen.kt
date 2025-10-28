package com.example.ngajiq.ui.iqra

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ngajiq.R

private val ColorPrimaryBlue = Color(0xFF86E1FF)
private val ColorButtonBlue = Color(0xFF55D5FF)
private val ColorLightBlue = Color(0xFFCEF0FF)
private val ColorBlackText = Color(0xFF353535)
private val ColorButtonShadowLanjut = Color(0xFF42BBE4)
private val ColorButtonShadowReview = Color(0xFFD2E7FF)
private val ColorWhite = Color(0xFFFFFFFF)

@Composable
fun LearningCompletionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorWhite),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(278.dp)
                .background(Color.Transparent),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.confetti),
                contentDescription = "Confetti Background",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Text(
                text = "Hasil Belajar",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = ColorBlackText,
                modifier = Modifier.padding(top = 218.dp)
            )
        }

        PercentageCircle(percentage = 100)

        Spacer(modifier = Modifier.height(48.dp))

        ScoreBox(correct = 1, incorrect = 0)

        Spacer(modifier = Modifier.height(44.dp))

        GameButton(
            text = "Lanjut",
            onClick = { /* TODO: Navigasi ke Roadmap Level */ },
            buttonColor = ColorButtonBlue,
            shadowColor = ColorButtonShadowLanjut,
            textColor = ColorWhite,
            modifier = Modifier
                .width(242.dp)
                .padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        GameButton(
            text = "Review Jawaban",
            onClick = { /* TODO: Navigasi ke Review Jawaban */ },
            buttonColor = ColorWhite,
            shadowColor = ColorButtonShadowReview,
            textColor = ColorBlackText,
            modifier = Modifier
                .width(242.dp)
                .padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun PercentageCircle(percentage: Int) {
    val size = 150.dp
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .border(20.dp, ColorLightBlue, CircleShape)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$percentage%",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = ColorBlackText
        )
    }
}

@Composable
fun ScoreBox(correct: Int, incorrect: Int) {
    Row(
        modifier = Modifier
            .width(288.dp)
            .height(104.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(2.dp, ColorLightBlue, RoundedCornerShape(16.dp))
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ScoreItem(
            value = correct.toString(),
            label = "Benar",
            modifier = Modifier.fillMaxHeight()
        )
        ScoreItem(
            value = incorrect.toString(),
            label = "Salah",
            modifier = Modifier.fillMaxHeight()
        )
    }
}

@Composable
fun ScoreItem(value: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.width(144.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = value,
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = ColorBlackText
        )
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = ColorBlackText
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLearningCompletionScreen() {
    LearningCompletionScreen()
}
