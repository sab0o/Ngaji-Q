package com.example.ngajiq.ui.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavHostController) {
    val categories: List<Category> = HomeRepository.getCategories()
    val recommendations: List<Recommendation> = HomeRepository.getRecommendations()
    val featuredCard: Recommendation = HomeRepository.getFeaturedCard()

    HomeScreenContent(
        categories = categories,
        recommendations = recommendations,
        featuredCard = featuredCard,
        onCategoryClick = { /* TODO */ },
        onRecommendationClick = { /* TODO */ }
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
    val overlapHeight = 28.dp

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        // HEADER
        HeaderSection()

        // WHITE CARD CONTAINER
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = -overlapHeight)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
                )
                .padding(top = 24.dp)
        ) {

            // SEARCH BAR
            SearchBarSection()

            // CATEGORIES
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    text = "Kategori",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))

                categories.chunked(3).forEach { rowCategories ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        rowCategories.forEach { category ->
                            CategoryItem(
                                category = category,
                                onClick = { onCategoryClick(category) }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // RECOMMENDATION TITLE
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Rekomendasi",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Lihat semua",
                    fontSize = 14.sp,
                    color = Color(0xFF4A90E2),
                    modifier = Modifier.clickable { }
                )
            }

            // RECOMMENDATION LIST
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 24.dp)
            ) {
                items(recommendations) { item ->
                    RecommendationItem(
                        recommendation = item,
                        onClick = { onRecommendationClick(item) }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(rememberNavController())
}
