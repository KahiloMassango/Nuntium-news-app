package com.example.nuntium.ui.NuntiumApp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.nuntium.ui.nvgraph.Route

class MainViewModel: ViewModel() {

    var startDestination by mutableStateOf(Route.HomeScreen.route)
        private set


}