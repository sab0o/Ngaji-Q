package com.example.ngajiq.ui.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ngajiq.data.model.Category
import com.example.ngajiq.data.model.Recommendation
import com.example.ngajiq.data.repository.HomeRepository
import com.example.ngajiq.ui.main.home.components.*

@Composable
fun HomeScreen(navController: NavHostController) {
    val categories = HomeRepository.getCategories()
    val recommendations = HomeRepository.getRecommendations()
    val featuredCard = HomeRepository.getFeaturedCard()

    // ❌ Jangan pakai Scaffold di sini, sudah ada di MainScreen
    HomeScreenContent(
        categories = categories,
        recommendations = recommendations,
        featuredCard = featuredCard,
        onCategoryClick = { /* TODO: navigate ke halaman kategori */ },
        onRecommendationClick = { /* TODO: navigate ke detail rekomendasi */ }
    )
}

@Composable
fun HomeScreenContent(
    categories: List<Category>,
    recommendations: List<Recommendation>,
    featuredCard: Recommendation,
    modifier: Modifier = Modifier,
    onCategoryClick: (Category) -> Unit = {},
    onRecommendationClick: (Recommendation) -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .background(Color.White)
    ) {
        HeaderSection()
        SearchBarSection()

        // --- KATEGORI ---
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(text = "Kategori", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            categories.chunked(3).forEach { rowCategories ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    rowCategories.forEach { category ->
                        CategoryItem(category = category, onClick = { onCategoryClick(category) })
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        // --- REKOMENDASI ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Rekomendasi Belajar", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(
                text = "Lihat semua >>",
                fontSize = 12.sp,
                color = Color.Blue.copy(alpha = 0.8f),
                modifier = Modifier.clickable { }
            )
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            items(recommendations) { item ->
                RecommendationCard(
                    item = item,
                    modifier = Modifier
                        .width(260.dp)
                        .clickable { onRecommendationClick(item) }
                )
            }
        }

        // --- FEATURED CARD ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            RecommendationCard(
                item = featuredCard,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onRecommendationClick(featuredCard) }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}
