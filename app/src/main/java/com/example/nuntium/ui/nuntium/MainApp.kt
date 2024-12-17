package com.example.nuntium.ui.theme.nuntium

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {
    val showBottomBar: Boolean = navController
        .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes

    Scaffold(
        bottomBar = {
            if(showBottomBar){
                BottomBar(navController)
            }
        }
    ) { paddingValue ->
        NavGraph(
            modifier = Modifier.padding(paddingValue),
            navController = navController,
            startDestination = startDestination,
        )
    }
}