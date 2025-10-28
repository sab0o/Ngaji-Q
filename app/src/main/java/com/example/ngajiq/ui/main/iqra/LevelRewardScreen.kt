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
import androidx.compose.ui.unit.Dp
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
private val ColorStarYellow = Color(0xFFFFCC00)

//
@Composable
fun GameButton(
    text: String,
    onClick: () -> Unit,
    buttonColor: Color,
    shadowColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    buttonHeight: Dp = 53.dp,
    cornerRadius: Dp = 20.dp
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val buttonOffset = if (isPressed) 2.dp else 6.dp

    Box(
        modifier = modifier
            .padding(top = 2.dp)
            .height(buttonHeight + buttonOffset),
        contentAlignment = Alignment.TopCenter
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(cornerRadius))
                .background(shadowColor)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .offset(y = (-buttonOffset))
                .clip(RoundedCornerShape(cornerRadius))
                .background(buttonColor)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text.uppercase(),
                color = textColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
            )
        }
    }
}

@Composable
fun PointBadge(points: Int) {
    Row(
        modifier = Modifier
            .width(118.dp)
            .height(32.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(ColorButtonBlue)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "+$points Poin",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = ColorWhite
        )
    }
}

@Composable
fun LevelProgressCircle(
    userLevel: Int,
    progressPercentage: Float,
    profileResId: Int = R.drawable.profile_placeholder
) {
    val size = 150.dp
    val trackColor = ColorLightBlue
    val ringWidth = 20.dp

    Box(
        modifier = Modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(trackColor)
        )

        Image(
            painter = painterResource(id = profileResId),
            contentDescription = "User Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(size - ringWidth * 2)
                .clip(CircleShape)
                .background(Color.White)
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = 10.dp)
                .size(36.dp)
                .clip(CircleShape)
                .background(ColorWhite)
                .border(2.dp, ColorPrimaryBlue, CircleShape), // Border putih
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = userLevel.toString(),
                color = ColorPrimaryBlue,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun LevelRewardScreen(
    userLevel: Int = 1,
    pointsEarned: Int = 50,
    progressPercentage: Float = 1.0f
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorWhite)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Transparent),
            contentAlignment = Alignment.TopCenter
        ) {
//            Image(
//                painter = painterResource(id = R.drawable.confetti),
//                contentDescription = "Confetti Background",
//                contentScale = ContentScale.FillWidth,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        LevelProgressCircle(
            userLevel = userLevel,
            progressPercentage = progressPercentage,
            profileResId = R.drawable.profile_placeholder
        )

        Spacer(modifier = Modifier.height(40.dp))

//        Image(
//            painter = painterResource(id = R.drawable.starsallstraight),
//            contentDescription = "Reward Stars",
//            contentScale = ContentScale.Fit,
//            modifier = Modifier.width(200.dp).height(50.dp)
//        )

        Spacer(modifier = Modifier.height(24.dp))

        PointBadge(points = pointsEarned)

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Bagus! Kamu berhasil menyelesaikan pembelajaran ini",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = ColorBlackText,
            textAlign = TextAlign.Center,
            lineHeight = 28.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(60.dp))

        GameButton(
            text = "Lanjut",
            onClick = { },
            buttonColor = ColorButtonBlue,
            shadowColor = ColorButtonShadowLanjut,
            textColor = ColorWhite,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLevelRewardScreen() {
    LevelRewardScreen()
}
