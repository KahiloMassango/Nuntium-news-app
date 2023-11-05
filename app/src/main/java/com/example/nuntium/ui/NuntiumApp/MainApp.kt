package com.example.nuntium.ui.NuntiumApp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.nuntium.ui.nvgraph.NavGraph

@Composable
fun MainApp(
    viewModel: MainViewModel = MainViewModel()
) {
    val startDestination = viewModel.startDestination

    NavGraph(
        modifier = Modifier.fillMaxSize(),
        startDestination = startDestination
    )
}