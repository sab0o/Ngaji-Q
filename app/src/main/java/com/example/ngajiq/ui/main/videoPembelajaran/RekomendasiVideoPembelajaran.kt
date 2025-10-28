package com.example.ngajiq.ui.main.videoPembelajaran

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ngajiq.data.repository.LocalVideoRepository
import com.example.ngajiq.ui.common.SearchInputField
import com.example.ngajiq.ui.theme.AppLightBackgroundBlue
import com.example.ngajiq.ui.theme.NgajiQTheme

@Composable
fun VideoPembelajaran(repo: LocalVideoRepository) {
    var searchQuery by remember { mutableStateOf("") }

    // Filter videos dynamically based on search text
    val videoList = repo.videos.filter {
        it.title.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
       SearchInputField(searchQuery=searchQuery, onSearchChange = {searchQuery=it})
        // 📜 Video List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(videoList) { video ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(AppLightBackgroundBlue)
                        .clickable {
                            // TODO: handle click (navigate to video player)
                        }
                ) {
                    Image(
                        painter = painterResource(id = video.thumbnailRes),
                        contentDescription = video.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )

                    Text(
                        text = video.title,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VideoPembelajaranPreview() {
    val repository = LocalVideoRepository
    NgajiQTheme { VideoPembelajaran(repository) }
}
