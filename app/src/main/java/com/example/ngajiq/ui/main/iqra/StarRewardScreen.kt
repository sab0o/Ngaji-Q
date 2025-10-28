package com.example.ngajiq.ui.iqra

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ngajiq.R

private val ColorPrimaryBlue = Color(0xFF86E1FF)
private val ColorButtonBlue = Color(0xFF55D5FF)
private val ColorLightBlue = Color(0xFFCEF0FF)
private val ColorBlackText = Color(0xFF353535)
private val ColorButtonShadowLanjut = Color(0xFF42BBE4)
private val ColorWhite = Color(0xFFFFFFFF)
private val ColorStarYellow = Color(0xFFFFCC00)

@Composable
fun SuccessScreen(starsEarned: Int = 2, pointsEarned: Int = 50) {
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
                text = "Selamat!",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = ColorBlackText,
                modifier = Modifier.padding(top = 218.dp) //
            )
        }
        Spacer(modifier = Modifier.height(32.dp))

        StarDisplay(starsEarned = starsEarned, totalStars = 3)

        Spacer(modifier = Modifier.height(24.dp))

        PointBadge(points = pointsEarned)

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Bagus! Kamu berhasil menyelesaikan pembelajaran ini",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = ColorBlackText,
            textAlign = TextAlign.Center,
            lineHeight = 28.sp, //
            modifier = Modifier.fillMaxWidth(0.7f)
        )

        Spacer(modifier = Modifier.height(40.dp))

        GameButton(
            text = "Lanjut",
            onClick = { },
            buttonColor = ColorButtonBlue,
            shadowColor = ColorButtonShadowLanjut,
            textColor = ColorWhite,
            modifier = Modifier
                .width(242.dp)
                .padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(202.dp))
    }
}

@Composable
fun StarDisplay(starsEarned: Int, totalStars: Int) {
    Box(modifier = Modifier.width(315.dp).height(120.dp), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.starsall),
            contentDescription = "Star Reward",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize(),
            alpha = 1.0f
        )

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSuccessScreen() {
    SuccessScreen()
}
