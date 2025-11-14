package com.example.ngajiq.ui.main.kategori

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ngajiq.data.model.Video
import com.example.ngajiq.data.repository.LocalVideoRepository
import com.example.ngajiq.ui.common.SearchInputField
import com.example.ngajiq.ui.main.videoPembelajaran.VideoCard
import com.example.ngajiq.ui.theme.AppChipSelectedBlue
import com.example.ngajiq.ui.theme.AppDarkBlue
import com.example.ngajiq.ui.theme.AppHeaderBlue
import com.example.ngajiq.ui.theme.NgajiQTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KategoriVideoPembelajaranScreen(
    repo: LocalVideoRepository = LocalVideoRepository,
    onBackClick: () -> Unit = {},
    onVideoClick: (Video) -> Unit = {},
    selectedCategory: String
) {
    var searchQuery by remember { mutableStateOf("") }

    var selectedCategoryState by remember(selectedCategory) { mutableStateOf(selectedCategory) }

    val filteredVideos = remember(searchQuery, selectedCategoryState, repo.videos) {
        repo.videos
            .filter { it.category == selectedCategoryState }
            .filter { it.title.contains(searchQuery, ignoreCase = true) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Kategori",
                        color = AppDarkBlue,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = AppDarkBlue
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = AppHeaderBlue)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFE0F2F7))
        ) {
            // 🔍 Search Bar
            SearchInputField(
                searchQuery = searchQuery,
                onSearchChange = { searchQuery = it }
            )

            // 🟦 Category Chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repo.categories.forEach { category ->
                    FilterChip(
                        selected = selectedCategoryState == category,
                        onClick = { selectedCategoryState = category },
                        label = { Text(category) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = AppChipSelectedBlue,
                            selectedLabelColor = AppDarkBlue,
                            containerColor = Color.White,
                            labelColor = Color.DarkGray
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
                }
            }

            // 📺 Video List
            if (filteredVideos.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Tidak ada video di kategori ini.", color = Color.Gray)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(filteredVideos) { video ->
                        VideoCard(video = video, onVideoClick = onVideoClick)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 360)
@Composable
fun KategoriScreenPreview() {
    val repository = LocalVideoRepository
    NgajiQTheme {
        KategoriVideoPembelajaranScreen(repository, selectedCategory = "wudhu")
    }
}
