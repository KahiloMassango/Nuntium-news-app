package com.example.nuntium.ui.nvgraph

sealed class Route(
    val route: String
){
    data object OnBoardingScreen: Route("onBoardingScreen")
    data object MainScreens: Route("mainScreens")
    data object HomeScreen: Route("homeScreen")
    data object ArticleScreen: Route("articleScreen")
    data object SavedScreen: Route("savedScreen")
    data object CategoryScreen: Route("categoryScreen")
    data object LanguageScreen: Route("languageScreen")
    data object PreferenceScreen: Route("preferenceScreen")
    data object WelcomeScreen: Route("welcomeScreen")
}

private val bottomBarScreens: List<Route> = listOf(
    Route.HomeScreen,
    Route.SavedScreen,
    Route.CategoryScreen,
    Route.PreferenceScreen
)

val bottomBarRoutes = bottomBarScreens.map { it.route }

