package com.example.ngajiq.ui.iqra

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
//private val ColorButtonShadow = Color(0xFF42BBE4)
private val ColorButtonShadowLanjut = Color(0xFF42BBE4)
private val ColorWhite = Color(0xFFFFFFFF)

@Composable
fun ListenIqraScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorWhite)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Profile with border dan glow biru muda
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(ColorPrimaryBlue.copy(alpha = 0.3f), shape = CircleShape)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(Color.White, shape = CircleShape)
                    .border(4.dp, Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_placeholder),
                    contentDescription = "Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
            }

            // Progress bar placeholder
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(16.dp)
                    .padding(horizontal = 12.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(ColorLightBlue)
            )

            // Pause button
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(ColorButtonBlue)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_pause),
                    contentDescription = "Pause Button",
                    modifier = Modifier
                        .size(24.dp) // Smaller image size
//                    .clip(RoundedCornerShape(12.dp))
//                    .background(ColorButtonBlue)
//                    .clickable { }
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Speaker button
        SpeakerButton(
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Iqra image
        Box(
            modifier = Modifier
                .size(200.dp)
                .border(width = 2.dp, color = ColorPrimaryBlue, shape = RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White),

            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.iqra_sample),
                contentDescription = "Iqra Letter",
                modifier = Modifier.size(120.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Bacaan iqra text
        Box(
            modifier = Modifier
                .border(width = 2.dp, color = ColorPrimaryBlue, shape = RoundedCornerShape(12.dp))
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 24.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Ba",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF353535)
            )
        }


        Spacer(modifier = Modifier.height(80.dp))

        // Button lanjut
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
    }
}



@Composable
fun SpeakerButton(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Box(
        modifier = modifier
            .size(58.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF55D5FF))
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color(0xFF42BBE4),
                spotColor = Color(0xFF42BBE4)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_speaker),
            contentDescription = "Speaker",
            modifier = Modifier.size(28.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewListenIqraScreen() {
    ListenIqraScreen()
}
