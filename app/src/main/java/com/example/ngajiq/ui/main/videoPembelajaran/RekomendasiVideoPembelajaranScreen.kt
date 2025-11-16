package com.example.ngajiq.ui.main.videoPembelajaran

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import com.example.ngajiq.data.repository.SubjectRepository
import com.example.ngajiq.ui.common.SearchInputField
import com.example.ngajiq.ui.main.kategori.components.VideoPembelajaranScaffold
import com.example.ngajiq.ui.theme.NgajiQTheme
import com.example.ngajiq.ui.theme.SoftAzure

@Composable
fun RekomendasiVideoPembelajaranScreen(
    repo: SubjectRepository,
    onBackClick: () -> Unit = {},
) {
    var searchQuery by remember { mutableStateOf("") }

    val subjectList = repo.subjects
        .shuffled()
        .filter { it.title.contains(searchQuery, ignoreCase = true) }

    VideoPembelajaranScaffold(
        title = "Rekomendasi",
        onBackClick = onBackClick
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
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
                items(subjectList) { subject ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(4.dp, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                            .background(SoftAzure)
                            .clickable {
                                // TODO: onClick video
                            }
                    ) {
                        Image(
                            painter = painterResource(id = subject.thumbnailRes),
                            contentDescription = subject.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                        )

                        Text(
                            text = subject.title,
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

@Preview(showBackground=  true)
@Composable
fun RekomendasiVideoPembelajaranScreenPreview(){
    NgajiQTheme {
        RekomendasiVideoPembelajaranScreen(
            repo = SubjectRepository,
        )
    }
}

