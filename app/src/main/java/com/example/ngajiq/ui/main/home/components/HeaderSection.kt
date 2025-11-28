package com.example.ngajiq.ui.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ngajiq.R
@Composable
fun HeaderSection(username: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp) // Setting an explicit height to make the header longer
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF86D1FF),  // top
                        Color(0xFF5696F5)   // bottom
                    )
                )
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Profile Photo Placeholder + Badge
            Box(
                modifier = Modifier.size(60.dp)
            ) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clip(CircleShape)
                        .background(Color(0xFFB3E5FC))
                        .border(2.dp, Color(0xFFF7F7F7), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.brokolibenar),
                        contentDescription = "Profile Photo",

                        contentScale = ContentScale.Crop,

                        modifier = Modifier
                            .matchParentSize()
                            .clip(CircleShape)
                    )
                }

            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Assalamu'alaikum",
                    fontSize = 14.sp,
                    color = Color.White
                )
                Text(
                    text = username,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderSectionPreview() {
    HeaderSection(username = "Hamba Allah")
}