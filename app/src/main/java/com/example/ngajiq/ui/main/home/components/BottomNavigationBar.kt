package com.example.ngajiq.ui.main.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ngajiq.ui.navigation.Routes
import com.example.ngajiq.ui.theme.DeepBlue


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



private fun isTopLevelDestination(dest: NavDestination?, route: String): Boolean =
    dest?.hierarchy?.any { it.route == route } == true


@Composable
fun BottomNavigationBar(navController: NavHostController) {
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
