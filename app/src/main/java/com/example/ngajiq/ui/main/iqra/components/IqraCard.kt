package com.example.ngajiq.ui.main.iqra.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ngajiq.ui.theme.AquaBlue
import com.example.ngajiq.ui.theme.IceBlue

@Composable
fun IqraCard(
    iqraNumber: Int,
    title: String,
    currentLevel: Int,
    totalLevel: Int,
    imageRes: Int,
    onClick: () -> Unit = {},
    lockedState: Boolean,
) {
    val percentage = (currentLevel.toFloat() / totalLevel) * 100
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        shadowElevation = 8.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (lockedState) Color(0xFFACACAC) else AquaBlue)
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "Iqra $iqraNumber",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    text = title,
                    color = Color(0xFF353535),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "$currentLevel/$totalLevel level",
                    color = Color(0xFF4A4A4A),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(Modifier.height(12.dp))

                // Progress section
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Progress bar background
                    Box(
                        modifier = Modifier
                            .height(12.dp)
                            .weight(1f)
                            .clip(RoundedCornerShape(20.dp))
                            .background(if(lockedState) Color(0xFFDFDFDF) else IceBlue.copy(alpha = 0.4f))
                    ) {
                        // Fill
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(percentage / 100)
                                .clip(RoundedCornerShape(20.dp))
                                .background(if (lockedState) Color(0xFFDFDFDF) else AquaBlue)
                        )
                    }

                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = "${percentage.toInt()}%",
                        fontSize = 14.sp,
                        color = if (lockedState) Color(0xFF7A7A7A) else AquaBlue,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(Modifier.width(20.dp))

            // RIGHT SIDE (Iqra image)
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Iqra Image",
                modifier = Modifier.size(90.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
