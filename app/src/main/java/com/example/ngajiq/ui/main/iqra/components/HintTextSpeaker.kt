package com.example.ngajiq.ui.main.iqra.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ngajiq.R
import com.example.ngajiq.ui.theme.*

@Composable
fun HintTextWithSpeaker(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(50),
                ambientColor = SoftAzure,
                spotColor = SoftAzure
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // 🔊 Tombol speaker
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = PrimaryBlue,
                    shape = CircleShape
                )
                .clickable { onClick() }
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .size(40.dp),
                painter = painterResource(id = R.drawable.ic_speaker),
                contentDescription = "Speaker Icon"
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // 📝 Teks instruksi
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.Black,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        )
    }
}
