package com.shenzhen.housing.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shenzhen.housing.R
import com.shenzhen.housing.ui.screens.HomeScreen
import com.shenzhen.housing.ui.screens.PolicyScreen
import com.shenzhen.housing.ui.screens.ProjectsScreen
import com.shenzhen.housing.ui.screens.ApplicationScreen
import com.shenzhen.housing.ui.screens.MapScreen

@Composable
fun HousingNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                listOf(
                    Screen.Home to Icons.Default.Home,
                    Screen.Policy to Icons.Default.Policy,
                    Screen.Projects to Icons.Default.Search,
                    Screen.Application to Icons.Default.Info,
                    Screen.Map to Icons.Default.LocationOn
                ).forEach { (screen, icon) ->
                    NavigationBarItem(
                        icon = { Icon(icon, contentDescription = null) },
                        label = { Text(stringResource(screen.titleRes)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Policy.route) {
                PolicyScreen()
            }
            composable(Screen.Projects.route) {
                ProjectsScreen()
            }
            composable(Screen.Application.route) {
                ApplicationScreen()
            }
            composable(Screen.Map.route) {
                MapScreen()
            }
        }
    }
}
