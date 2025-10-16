package com.shenzhen.housing.ui.navigation

import com.shenzhen.housing.R

sealed class Screen(
    val route: String,
    val titleRes: Int
) {
    object Home : Screen("home", R.string.home)
    object Policy : Screen("policy", R.string.policy)
    object Projects : Screen("projects", R.string.projects)
    object Application : Screen("application", R.string.application)
    object Map : Screen("map", R.string.map)
}
