package com.example.nuntium.ui.NuntiumApp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nuntium.ui.common.BottomBar
import com.example.nuntium.ui.nvgraph.NavGraph
import com.example.nuntium.ui.nvgraph.bottomBarRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    viewModel: MainViewModel = MainViewModel(),
) {

    val navController: NavHostController = rememberNavController()
    val startDestination = viewModel.startDestination

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