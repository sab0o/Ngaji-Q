package com.example.ngajiq.ui.main.iqra.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ngajiq.R

@Composable
fun StarRating(percentage: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val starModifier = Modifier.size(80.dp)

        if (percentage > 0) { // 1-33 -> 1 star
            Image(
                painter = painterResource(id = R.drawable.stars),
                contentDescription = "Star 1",
                modifier = starModifier
            )
        }

        if (percentage > 33) { // 34-66 -> 2 stars
            Image(
                painter = painterResource(id = R.drawable.stars),
                contentDescription = "Star 2",
                modifier = starModifier
            )
        }

        if (percentage > 66) { // 67-100 -> 3 stars
            Image(
                painter = painterResource(id = R.drawable.stars),
                contentDescription = "Star 3",
                modifier = starModifier
            )
        }
    }
}