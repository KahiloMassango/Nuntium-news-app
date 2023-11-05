package com.example.nuntium.ui.nvgraph

import ArticleScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.nuntium.ui.screens.category.CategoryScreen
import com.example.nuntium.ui.screens.favorites.FavoritesScreen
import com.example.nuntium.ui.screens.home.HomeScreen
import com.example.nuntium.ui.screens.home.HomeViewModel
import com.example.nuntium.ui.screens.language.LanguageScreen
import com.example.nuntium.ui.screens.onboarding.OnBoardingScreen
import com.example.nuntium.ui.screens.preference.PreferenceScreen
import com.example.nuntium.ui.screens.welcome.WelcomeScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    startDestination: String,
    homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
){
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(Route.OnBoardingScreen.route) {
            OnBoardingScreen(navController = navController)
        }
        composable(Route.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }

        navigation(
            startDestination = Route.HomeScreen.route,
            route = Route.MainScreens.route
        ){
            composable(Route.HomeScreen.route,) {
                HomeScreen(homeViewModel = homeViewModel, navController = navController)
            }
            composable(Route.CategoryScreen.route) {
                CategoryScreen(homeViewModel = homeViewModel, navController = navController)
            }
            composable(Route.BookmarksScreen.route) {
                FavoritesScreen(navController = navController)
            }
            composable(Route.PreferenceScreen.route) {
                PreferenceScreen(navController = navController)
            }
            composable(Route.LanguageScreen.route) {
                LanguageScreen(navController = navController)
            }
            composable(Route.ArticleScreen.route) {
                ArticleScreen(navController = navController)
            }
        }
    }
}