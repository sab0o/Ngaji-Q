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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ngajiq.data.model.Category
import com.example.ngajiq.data.model.Recommendation
import com.example.ngajiq.data.model.Subject
import com.example.ngajiq.data.repository.HomeRepository
import com.example.ngajiq.ui.main.home.components.CategoryItem
import com.example.ngajiq.ui.main.home.components.HeaderSection
import com.example.ngajiq.ui.main.home.components.RecommendationCard
import com.example.ngajiq.ui.main.home.components.SearchBarSection
import com.example.ngajiq.ui.navigation.Routes
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(navController: NavHostController) {
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    val username = remember {
        currentUser?.displayName?.ifBlank { null }
            ?: currentUser?.email?.substringBefore("@")
            ?: "Teman Ngaji"
    }
    val categories: List<Category> = HomeRepository.getCategories()
    val recommendations: List<Subject> = HomeRepository.getRecommendations()
    val featuredCard: Recommendation = HomeRepository.getFeaturedCard()

    HomeScreenContent(
        navController=navController,
        username=username,
        categories = categories,
        recommendations = recommendations,
    )
}

@Composable
fun HomeScreenContent(
    username: String,
    navController: NavHostController,
    categories: List<Category>,
    recommendations: List<Subject>,
    modifier: Modifier = Modifier,
) {

    val scrollState = rememberScrollState()
    val overlapHeight = 28.dp

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        // HEADER
        HeaderSection(username)

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
                                onClick = { navController.navigate("${Routes.KATEGORI_SUBJECT}/${category.name}") }
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
                    RecommendationCard(
                        navController = navController,
                        subject = item,
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
    val navController = rememberNavController()
    HomeScreenContent(
        navController = navController,
        username = "Preview User", // Fake name avoids Firebase
        categories = HomeRepository.getCategories(), // This is safe if it's just a static list
        recommendations = HomeRepository.getRecommendations() // Or pass fake subjects here if you want to see them
    )
}
