package com.example.ngajiq

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ngajiq.ui.screen.AlifBacaJawabScreen
import com.example.ngajiq.ui.screen.FlashcardScreen
import com.example.ngajiq.ui.screen.ListLevelScreen
import com.example.ngajiq.ui.screen.ListenIqraScreen
import com.example.ngajiq.ui.screen.NulisAlifScreen
import com.example.ngajiq.ui.screen.SpellingAlifScreen
import com.example.ngajiq.ui.screen.LevelIqra1Screen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "listLevel"
    ) {
        composable("listLevel") { ListLevelScreen(navController) }
        composable("levelIqra1") { LevelIqra1Screen(navController)}
        composable("alifDengar") { SpellingAlifScreen(navController) }
        composable("nulisAlif") { NulisAlifScreen(navController) }
        composable("listenIqra") { ListenIqraScreen() }
        composable("alifBacaJawab") { AlifBacaJawabScreen(navController) }
        composable("flashcardAlif") { FlashcardScreen() }
    }
}
