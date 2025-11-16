package com.example.ngajiq.ui.main.videoPembelajaran

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ngajiq.data.model.Time
import com.example.ngajiq.ui.theme.PrimaryBlue

@Composable
fun DescriptionTimelineTabs(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    val tabs = listOf("Deskripsi", "Timeline")

    Row(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFE0F4FF), RoundedCornerShape(50))
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        tabs.forEach { tab ->
            val isSelected = tab == selectedTab

            Box(
                modifier = Modifier.Companion
                    .weight(1f)
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(50))
                    .background(
                        if (isSelected) Color(0xFF2A80F5)  // Blue
                        else Color(0xFFCFEFFF)             // Light blue
                    )
                    .clickable { onTabSelected(tab) }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Companion.Center
            ) {
                Text(
                    text = tab,
                    color = if (isSelected) Color.Companion.White else Color(0xFF4A6A85),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun DeskripsiContent(description: String?) {
    Column(modifier = Modifier.padding(16.dp)) {
        if (description != null) {
            Text(description)
        }
    }
}



@Composable
fun TimelineContent(timeline: List<Time>?) {
    Column(modifier = Modifier.padding(16.dp)) {
        timeline?.forEach { time ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp) // jarak antar row
                    .border(
                        width = 1.dp,
                        color = PrimaryBlue,
                        shape = RoundedCornerShape(8.dp) // border radius
                    )
                    .padding(8.dp) // padding di dalam row
            ) {
                Text(
                    text = time.label,
                    modifier = Modifier.weight(1f) // biar text label dan timestamp rata kiri-kanan
                )
                Text(text = time.timeStamp)
            }
        }
    }
}