package com.example.nuntium.ui.nvgraph

import ArticleScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.nuntium.ui.screens.favorites.FavoritesScreen
import com.example.nuntium.ui.screens.home.HomeScreen
import com.example.nuntium.ui.screens.language.LanguageScreen
import com.example.nuntium.ui.screens.onboarding.OnBoardingScreen
import com.example.nuntium.ui.screens.preference.PreferenceScreen
import com.example.nuntium.ui.screens.welcome.WelcomeScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    startDestination: String,
    navController: NavHostController
){

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(Route.OnBoardingScreen.route) {
            OnBoardingScreen(navController = navController)
        }
        composable(Route.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }
        composable(Route.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(Route.SavedScreen.route) {
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