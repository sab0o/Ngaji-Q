package com.example.ngajiq

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "listLevel"
    ) {
//        composable("listLevel") { ListLevelScreen(navController) }
//        composable("levelIqra1") { LevelIqra1Screen(navController)}
////        composable("alifDengar") { SpellingAlifScreen(navController) }
//        composable("nulisAlif") { NulisAlifScreen(navController) }
//        composable("listenIqra") { ListenIqraScreen() }
//        composable("alifBacaJawab") { AlifBacaJawabScreen(navController) }
//        composable("flashcardAlif") { FlashcardScreen() }
    }
}
