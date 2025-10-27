package com.example.ngajiq.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun GoogleButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        // Note: Replace 'R.drawable.ic_google' with your Google icon asset
//        Icon(
//            painter = painterResource(id = R.drawable.ic_google),
//            contentDescription = "Google Icon",
//            modifier = Modifier.size(24.dp),
//            tint = Color.Unspecified // Important for custom icons
//        )
        Spacer(Modifier.width(8.dp))
        Text("Lanjutkan dengan Google", color = Color.Black)
    }
}