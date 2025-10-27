package com.example.ngajiq.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ngajiq.ui.main.home.HomeScreen
import com.example.ngajiq.ui.main.profile.ProfileScreen


sealed class BottomItem(val route: String, val label: String, val icon:
androidx.compose.ui.graphics.vector.ImageVector) {
    data object Home : BottomItem(Routes.HOME, "Home", Icons.Filled.Home)
    data object Profile : BottomItem(Routes.PROFILE, "Profile",
        Icons.Filled.Person)
}

private val bottomItems = listOf(
    BottomItem.Home, BottomItem.Profile)
@Preview(showBackground=true)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar =  { BottomNavBar(navController)}
    ) {  padding ->
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

                        popUpTo(navController.graph.findStartDestination().id) { saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label)
                },
                label = { Text(item.label) }
            )
        }
    }
}

private fun isTopLevelDestination(dest: NavDestination?, route: String):
        Boolean =
    dest?.hierarchy?.any { it.route == route } == true

@Composable
private fun MainNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) { HomeScreen(navController) }
        composable(Routes.PROFILE) { ProfileScreen() }
    }
}



