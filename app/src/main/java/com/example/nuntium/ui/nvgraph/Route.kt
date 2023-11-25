package com.example.nuntium.ui.nvgraph

sealed class Route(
    val route: String
){
    data object OnBoardingScreen: Route("onBoardingScreen")
    data object HomeScreen: Route("homeScreen")
    data object ArticleScreen: Route("articleScreen")
    data object SavedScreen: Route("savedScreen")
    data object PreferenceScreen: Route("preferenceScreen")
    data object WelcomeScreen: Route("welcomeScreen")
}

val bottomBarRoutes: List<String> = listOf(
    Route.HomeScreen.route,
    Route.SavedScreen.route,
    Route.PreferenceScreen.route
)

