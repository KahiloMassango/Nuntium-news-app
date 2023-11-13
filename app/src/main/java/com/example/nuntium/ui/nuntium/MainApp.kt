package com.example.nuntium.ui.nuntium

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nuntium.ui.commonUi.BottomBar
import com.example.nuntium.ui.nvgraph.NavGraph
import com.example.nuntium.ui.nvgraph.bottomBarRoutes
import com.example.nuntium.ui.screens.home.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    viewModel: MainAppViewModel = viewModel(factory = HomeViewModel.Factory),
) {

    val navController: NavHostController = rememberNavController()
    val startDestination = viewModel.startScreen


    val shouldShowBottomBar: Boolean = navController
        .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes

    Scaffold(
        bottomBar = {
            if(shouldShowBottomBar){
                BottomBar(navController)
            }
        }
    ) { paddingValue ->
        NavGraph(
            modifier = Modifier.padding(paddingValue),
            startDestination = startDestination,
            navController = navController
        )
    }

}