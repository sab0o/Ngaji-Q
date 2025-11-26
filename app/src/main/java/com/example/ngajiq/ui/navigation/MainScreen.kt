package com.example.ngajiq.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ngajiq.data.PracticeRepository
import com.example.ngajiq.data.model.HurufHijaiyah
import com.example.ngajiq.data.repository.HurufHijaiyahRepository
import com.example.ngajiq.data.repository.SubjectRepository
import com.example.ngajiq.ui.main.auth.LoginScreen
import com.example.ngajiq.ui.main.auth.RegisterScreen
import com.example.ngajiq.ui.main.home.HomeScreen
import com.example.ngajiq.ui.main.iqra.ListenHarakatScreen
import com.example.ngajiq.ui.main.iqra.ListeningScreen
import com.example.ngajiq.ui.main.iqra.MapIqra
import com.example.ngajiq.ui.main.iqra.MateriScreen
import com.example.ngajiq.ui.main.kategori.KategoriVideoPembelajaranScreen
import com.example.ngajiq.ui.main.maps.MapsScreen
import com.example.ngajiq.ui.main.practice.PracticeScreen
import com.example.ngajiq.ui.main.profile.ProfileScreen
import com.example.ngajiq.ui.main.subjectPembelajaran.PembelajaranScreen
import com.example.ngajiq.ui.main.videoPembelajaran.RekomendasiVideoPembelajaranScreen
import com.example.ngajiq.ui.theme.DeepBlue
import com.example.ngajiq.ui.main.iqra.WritingScreen
import com.example.ngajiq.ui.main.iqra.AlifJawabScreen
import com.example.ngajiq.ui.main.iqra.LearningCompletionScreen
import com.example.ngajiq.ui.main.iqra.FlashcardScreen
sealed class BottomItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Home : BottomItem(Routes.HOME, "Home", Icons.Filled.Home)
    data object Materi : BottomItem(Routes.MATERI, "Materi", Icons.Filled.Book)
    data object Quiz : BottomItem(Routes.CARINGAJI, "Ngaji", Icons.Filled.School)
    data object Profile : BottomItem(Routes.PROFILE, "Profil", Icons.Filled.Person)
}

private val bottomItems = listOf(
    BottomItem.Home,
    BottomItem.Materi,
    BottomItem.Quiz,
    BottomItem.Profile
)

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val bottomBarRoutes = listOf(
        Routes.HOME,
        Routes.MATERI,
        Routes.CARINGAJI,
        Routes.PROFILE
    )

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                BottomNavBar(navController)
            }
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {
            MainNavHost(navController)
        }
    }
}

@Composable
private fun BottomNavBar(navController: NavHostController) {
    val backStack by navController.currentBackStackEntryAsState()
    val dest = backStack?.destination

    NavigationBar {
        bottomItems.forEach { item ->
            val selected = isTopLevelDestination(dest, item.route)

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            tint = DeepBlue
                        )


                        Box(
                            modifier = Modifier
                                .height(2.dp)
                                .width(1.dp)
                                .background(
                                    if (selected) DeepBlue
                                    else Color.Transparent
                                )
                        )
                    }
                },
                label = { Text("") },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}


private fun isTopLevelDestination(dest: NavDestination?, route: String): Boolean =
    dest?.hierarchy?.any { it.route == route } == true


@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Routes.LOGIN) {
        composable("writingIqra/{hurufId}") { backStackEntry ->
            val hurufId = backStackEntry.arguments?.getString("hurufId")!!.toInt()

            var huruf: HurufHijaiyah? = null
            var iqraFound: Int? = null

            for (iqra in 1..6) {
                val list = HurufHijaiyahRepository.getListHurufById(iqra)
                val match = list.firstOrNull { it.id == hurufId }
                if (match != null) {
                    huruf = match
                    iqraFound = iqra
                    break
                }
            }

            if (huruf == null || iqraFound == null) return@composable

            WritingScreen(
                huruf = huruf!!,
                iqraId = iqraFound!!,
                navController = navController
            )
        }


        composable("${Routes.LISTENINGIQRA}/{iqraId}/{hurufId}") { backStackEntry ->

            // NULL? → langsung set default
            val iqraId = backStackEntry.arguments?.getString("iqraId")?.toIntOrNull() ?: 1
            val hurufId = backStackEntry.arguments?.getString("hurufId")?.toIntOrNull() ?: 1

            // Kalau hurufId gak ada di IQRA tsb, Fallback ke index pertama
            val listHuruf = HurufHijaiyahRepository.getListHurufById(iqraId)
            val huruf = listHuruf.firstOrNull { it.id == hurufId } ?: listHuruf.first()

            // GAS masuk layar
            ListeningScreen(huruf, navController)
        }



        composable(Routes.HOME) { HomeScreen(navController) }
        composable(Routes.MATERI) { MateriScreen(selectedItem=1, navController) }
        composable(Routes.LOGIN) { LoginScreen(
            onNavigateToRegister= {navController.navigate(Routes.REGISTER)},
            onNavigateToHome = {navController.navigate(Routes.HOME)}


        ) }
        composable(Routes.REGISTER) { RegisterScreen(
            onNavigateToLogin = { navController.navigate(Routes.LOGIN) },
            onNavigateToHome = {navController.navigate(Routes.HOME)}
        )}
        composable(Routes.CARINGAJI) { MapsScreen() }
        composable(Routes.PROFILE) { ProfileScreen() }
        composable(
            route = "${Routes.KATEGORI_SUBJECT}/{categoryName}"
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName").orEmpty()
            KategoriVideoPembelajaranScreen(selectedCategory = categoryName, onBackClick = { navController.popBackStack() }, navController = navController)
        }
        composable(Routes.REKOMENDASI) {
            RekomendasiVideoPembelajaranScreen(
                repo = SubjectRepository,
                onBackClick = {navController.popBackStack()},

            )
        }
        composable(route="${Routes.PRACTICE}/{categoryName}/{practiceId}"){
            backStackEntry ->
            val categoryName= backStackEntry.arguments?.getString("categoryName").orEmpty()
            val practiceId = backStackEntry.arguments?.getString("practiceId")?.toIntOrNull()
            PracticeScreen(repo = PracticeRepository,categoryName, practiceId)
        }
        composable(
            route = "${Routes.SUBJECT}/{categoryName}/{subjectId}"
        ) { backStackEntry ->

            val categoryName = backStackEntry.arguments
                ?.getString("categoryName")
                .orEmpty()

            val subjectId = backStackEntry.arguments
                ?.getString("subjectId")?.toIntOrNull()

            PembelajaranScreen(
                navController=navController,
                repo = SubjectRepository,
                category = categoryName,
                subjectId = subjectId,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(route="${Routes.MAPIQRA}/{iqraId}"){
            backStackEntry ->
            val iqraId = backStackEntry.arguments?.getString("iqraId")?.toIntOrNull()
            MapIqra(iqraId, navController)
        }

    }
}

