package com.example.ngajiq.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ngajiq.data.repository.LocalVideoRepository
import com.example.ngajiq.ui.main.auth.LoginScreen
import com.example.ngajiq.ui.main.auth.RegisterScreen
import com.example.ngajiq.ui.main.home.HomeScreen
import com.example.ngajiq.ui.main.iqra.MateriScreen
import com.example.ngajiq.ui.main.kategori.KategoriVideoPembelajaranScreen
import com.example.ngajiq.ui.main.profile.ProfileScreen
import com.example.ngajiq.ui.main.videoPembelajaran.RekomendasiVideoPembelajaranScreen

sealed class BottomItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Home : BottomItem(Routes.HOME, "Home", Icons.Filled.Home)
    data object Materi : BottomItem(Routes.MATERI, "Materi", Icons.Filled.Book)
    data object Quiz : BottomItem(Routes.ADD, "Ngaji", Icons.Filled.School)
    data object Profile : BottomItem(Routes.PROFILE, "Profil", Icons.Filled.Person)
}

private val bottomItems = listOf(
    BottomItem.Home,
    BottomItem.Materi,
    BottomItem.Quiz,
    BottomItem.Profile
)

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
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
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}

private fun isTopLevelDestination(dest: NavDestination?, route: String): Boolean =
    dest?.hierarchy?.any { it.route == route } == true


@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Routes.LOGIN) {
        composable(Routes.HOME) { HomeScreen(navController) }
        composable(Routes.MATERI) { MateriScreen(selectedItem=1, navController) }
        composable(Routes.LOGIN) { LoginScreen(
            onLoginClick = { navController.navigate(Routes.HOME) },
            onForgotPasswordClick = { navController.navigate(Routes.HOME) }
        ) }
        composable(Routes.REGISTER) { RegisterScreen(
            onRegisterClick = { navController.navigate(Routes.HOME) }
        )}
        composable(Routes.ADD) { Text("Halaman Quiz") }
        composable(Routes.PROFILE) { ProfileScreen() }
        composable(
            route = "${Routes.KATEGORI_VIDEO}/{categoryName}"
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
            KategoriVideoPembelajaranScreen(selectedCategory = categoryName, onBackClick = { navController.popBackStack() })
        }
        composable(Routes.REKOMENDASI) {
            RekomendasiVideoPembelajaranScreen(
                repo = LocalVideoRepository,
                onBackClick = {navController.popBackStack()}
            )
        }
    }
}

