package com.example.nuntium.ui.nvgraph

sealed class Route(
    val route: String
){
    data object OnBoardingScreen: Route("onBoardingScreen")
    data object MainScreens: Route("mainScreens")
    data object HomeScreen: Route("homeScreen")
    data object ArticleScreen: Route("articleScreen")
    data object BookmarksScreen: Route("bookmarksScreen")
    data object CategoryScreen: Route("categoryScreen")
    data object LanguageScreen: Route("languageScreen")
    data object PreferenceScreen: Route("preferenceScreen")
    data object WelcomeScreen: Route("welcomeScreen")
}
