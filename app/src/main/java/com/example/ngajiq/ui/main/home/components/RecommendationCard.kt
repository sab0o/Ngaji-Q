package com.example.ngajiq.ui.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ngajiq.data.model.Subject
import com.example.ngajiq.ui.navigation.Routes
import com.example.ngajiq.ui.theme.IceBlue


@Composable
fun RecommendationCard(subject: Subject, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(300.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(IceBlue)
            .clickable { navController.navigate("${Routes.SUBJECT}/${subject.category}/${subject.id}") }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Image(
                painter = painterResource(id = subject.thumbnailRes),
                contentDescription = subject.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            // 🎞 Duration
            Text(
                text = subject.duration,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(10.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )

        }

        // 📘 Title
        Text(
            text = subject.title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, name = "Long Title Preview")
@Composable
fun RecommendationCardPreview() {
    val navController = rememberNavController()
    val dummySubject = Subject(
        id = 12,
        title = "Pembelajaran Fiqih Lanjutan: Memahami Rukun dan Syarat Sah Sholat Wajib Bagi Pemula",
        thumbnailRes = android.R.drawable.ic_menu_gallery,
        category = "Fiqih",
        duration = "25:00"
    )

    MaterialTheme {
        Box(modifier = Modifier.padding(24.dp)) {
            RecommendationCard(
                subject = dummySubject,
                navController = navController
            )
        }
    }
}
