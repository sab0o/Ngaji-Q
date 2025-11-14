package com.example.ngajiq.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ngajiq.data.repository.LocalVideoRepository
import com.example.ngajiq.ui.iqra.MateriScreen
import com.example.ngajiq.ui.main.auth.LoginScreen
import com.example.ngajiq.ui.main.auth.RegisterScreen
import com.example.ngajiq.ui.main.home.HomeScreen
import com.example.ngajiq.ui.main.kategori.KategoriVideoPembelajaranScreen
import com.example.ngajiq.ui.main.profile.ProfileScreen
import com.example.ngajiq.ui.main.splash.SplashScreen
import com.example.ngajiq.ui.main.videoPembelajaran.RekomendasiVideoPembelajaranScreen

@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(
                navController
            )
        }
        composable(Routes.MAIN_GRAPH) {
            MainScreen()
        }
    }
}
@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Routes.HOME) {
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
