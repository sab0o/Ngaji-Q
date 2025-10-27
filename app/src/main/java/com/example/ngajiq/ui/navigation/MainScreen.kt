package com.example.ngajiq.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ngajiq.ui.main.home.HomeScreen
import com.example.ngajiq.ui.main.profile.ProfileScreen


sealed class BottomItem(val route: String, val label: String, val icon:
androidx.compose.ui.graphics.vector.ImageVector) {
    data object Home : BottomItem(Routes.HOME, "Home", Icons.Filled.Home)
    data object Profile : BottomItem(Routes.PROFILE, "Profile",
        Icons.Filled.Person)
    data object Settings : BottomItem(Routes.SETTINGS, "Settings",
        Icons.Filled.Settings)
    data object Notification : BottomItem(Routes.NOTIFICATION, "Notifications",
        Icons.Filled.Notifications)
}


@Preview(showBackground=true)
@Composable
fun MainScreen() {
    var selectedItem by remember { mutableIntStateOf(1) }
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.AccountBox, contentDescription = "Belajar") },
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Star, contentDescription = "Kelas") },
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profil") },
                    selected = selectedItem == 3,
                    onClick = { selectedItem = 3 }
                )
            }
        }
    ) {  padding ->
        Box(Modifier.padding(padding)) {
            MainNavHost(navController)
        }
    }
}

@Composable
private fun MainNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) { HomeScreen(navController) }
        composable(Routes.PROFILE) { ProfileScreen() }

    }
}

