package com.example.ngajiq.ui.main.kategori

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ngajiq.data.model.Subject
import com.example.ngajiq.data.repository.SubjectRepository
import com.example.ngajiq.ui.common.SearchInputField
import com.example.ngajiq.ui.main.kategori.components.VideoPembelajaranScaffold
import com.example.ngajiq.ui.main.subjectPembelajaran.SubjectCard
import com.example.ngajiq.ui.theme.NgajiQTheme
import com.example.ngajiq.ui.theme.PrimaryBlue

@Composable
fun KategoriVideoPembelajaranScreen(
    repo: SubjectRepository = SubjectRepository,
    navController: NavController,
    onBackClick: () -> Unit = {},
    selectedCategory: String = "Fiqih"
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategoryState by remember(selectedCategory) { mutableStateOf(selectedCategory) }

    val filteredSubjects = remember(searchQuery, selectedCategoryState) {
        repo.getSubjectsByCategory(selectedCategoryState)
            .filter { it.title.contains(searchQuery, ignoreCase = true) }
    }

    VideoPembelajaranScaffold (
        onBackClick = onBackClick
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
                        selectedContainerColor = PrimaryBlue,
                        selectedLabelColor = Color.White,
                        containerColor = Color.Transparent,
                        labelColor = PrimaryBlue
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = PrimaryBlue,
                        selectedBorderColor = PrimaryBlue,
                        enabled = true,
                        selected = false
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
            }
        }

        // 📺 Video List
        if (filteredSubjects.isEmpty()) {
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
                    .padding(vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredSubjects) { subject ->
                    SubjectCard(subject = subject, navController = navController)
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun KategoriScreenPreview() {
    var navController  = rememberNavController()
    NgajiQTheme {
        KategoriVideoPembelajaranScreen(
            repo = SubjectRepository,
            selectedCategory = "Fiqih",
            navController = navController
        )
    }
}
