package com.example.ngajiq.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ngajiq.ui.main.splash.SplashScreen

@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        // Splash tetap sama
        composable(Routes.SPLASH) {
            SplashScreen(
                navController
            )
        }
        // Ganti: sebelumnya 'navigation(route = MAIN_GRAPH) { ... }'
        // Sekarang: satu composable yang menampilkan MainScaffold
        composable(Routes.MAIN_GRAPH) {
            MainScreen()
        }
    }
}
