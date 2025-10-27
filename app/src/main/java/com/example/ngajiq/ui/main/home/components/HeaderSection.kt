package com.example.ngajiq.ui.main.home.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Image with Badge (Diganti dengan Box dan Icon standar)
        Box(
            modifier = Modifier.size(60.dp)
        ) {
            // Box menggantikan Image untuk profil
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(CircleShape)
                    .background(Color(0xFFB3E5FC)) // Light Blue Background
                    .border(2.dp, Color(0xFFF7F7F7), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                // Icon standar sebagai placeholder
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Profile Placeholder",
                    modifier = Modifier.size(40.dp),
                    tint = Color(0xFF0D47A1) // Dark Blue tint
                )
            }

            // Notification Badge '1'
            Box(
                modifier = Modifier
                    .offset(x = 4.dp, y = 4.dp)
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .align(Alignment.BottomStart)
                    .border(1.5.dp, Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "1", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Black)
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Text Content
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Assalamu'alaikum", fontSize = 14.sp, color = Color.Gray)
            Text(text = "Abd. Muiz", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        // XP Badge
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFFFCC00)) // Yellow Background
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "570 xp",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}