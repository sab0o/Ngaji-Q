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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.ngajiq.data.repository.LocalVideoRepository
import com.example.ngajiq.ui.common.SearchInputField
import com.example.ngajiq.ui.theme.NgajiQTheme
import com.example.ngajiq.ui.theme.PrimaryBlue
import com.example.ngajiq.ui.theme.SoftAzure


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RekomendasiVideoPembelajaranScreen(
    repo: LocalVideoRepository,
    onBackClick: () -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }

    val videoList = repo.videos.filter {
        it.title.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Rekomendasi",
                        color = PrimaryBlue,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = PrimaryBlue
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryBlue))
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            SearchInputField(
                searchQuery = searchQuery,
                onSearchChange = { searchQuery = it }
            )

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
                            .background(SoftAzure)
                            .clickable {
                                // Bisa navigate ke VideoPlayerScreen misal
                                // navController.navigate("video_player/${video.id}")
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
}
@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun RekomendasiVideoPembelajaranPreview() {
    val repository = LocalVideoRepository
    NgajiQTheme {
        RekomendasiVideoPembelajaranScreen(
            repo = repository,
            onBackClick = { /* preview, tidak melakukan apa-apa */ }
        )
    }
}
