package com.example.ngajiq.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ngajiq.R


@Composable
fun GoogleButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(50.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Color.LightGray),
    ) {
        // Note: Replace 'R.drawable.ic_google' with your Google icon asset
        Icon(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = "Google Icon",
            modifier = Modifier.size(24.dp),
            tint = Color.Unspecified // Important for custom icons
        )
        Spacer(Modifier.width(8.dp))
        Text("Lanjutkan dengan Google", color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun GoogleButtonPreview() {
    GoogleButton(onClick = {})
}