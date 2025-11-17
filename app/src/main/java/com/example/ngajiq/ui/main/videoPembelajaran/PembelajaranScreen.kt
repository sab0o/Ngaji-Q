package com.example.ngajiq.ui.main.subjectPembelajaran

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ngajiq.data.repository.SubjectRepository
import com.example.ngajiq.ui.common.YouTubeVideoPlayer
import com.example.ngajiq.ui.main.videoPembelajaran.DescriptionTimelineTabs
import com.example.ngajiq.ui.main.videoPembelajaran.DeskripsiContent
import com.example.ngajiq.ui.main.videoPembelajaran.TimelineContent
import com.example.ngajiq.ui.navigation.Routes
import com.example.ngajiq.ui.theme.PrimaryBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PembelajaranScreen(navController: NavController, repo: SubjectRepository, category: String, subjectId: Int?, onBackClick: () -> Unit = {}){
    val subject = repo.getSubjectFromCategory(category, subjectId)
    var selectedTab by remember { mutableStateOf("Deskripsi") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    subject?.title?.let {
                        Text(
                            text = it,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryBlue
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            YouTubeVideoPlayer(videoId = "dQw4w9WgXcQ")
            Column {
                DescriptionTimelineTabs(
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it },
                )

                if (selectedTab == "Deskripsi") {
                    DeskripsiContent(subject?.description)
                } else {
                    TimelineContent(subject?.timeline)
                }
            }
            Button(onClick = {navController.navigate("${Routes.PRACTICE}/${category}/${subjectId}")}
                ,modifier = Modifier
                    .padding(16.dp)
                    .width(200.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)) {
                Text(text = "Latihan")
            }

        }
    }
}


@Preview(showBackground=true)
@Composable
fun PembelajaranScreenPreview(){
    var repo = SubjectRepository
    var navController = rememberNavController()
    PembelajaranScreen(
        repo = repo,
        category = "Fiqih",
        subjectId = 1,
        onBackClick = { navController.popBackStack() },
        navController = navController
    )
}