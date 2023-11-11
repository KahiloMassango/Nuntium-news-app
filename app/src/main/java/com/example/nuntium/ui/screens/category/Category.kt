package com.example.nuntium.ui.screens.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.nuntium.R
import com.example.nuntium.ui.commonUi.CategoryCard
import com.example.nuntium.ui.commonUi.TopBar1
import com.example.nuntium.ui.commonUi.defaultPadding
import com.example.nuntium.ui.nvgraph.Route
import com.example.nuntium.ui.screens.home.HomeViewModel
import com.example.nuntium.ui.theme.NuntiumTheme


data class Category(
    val emoji:String,
    val name: String
)

val categoriesList = listOf(
    Category("\uD83C\uDFC8", name = "General"),
    Category("\uD83C\uDFC8", name = "Sports"),
    Category("⚖️", name = "Business"),
    Category("\uD83C\uDF1E", name = "Entertainment"),
    Category("\uD83D\uDC3B", name = "Health"),
    Category("\uD83C\uDF34", name = "Science"),
    Category("\uD83C\uDFA8", name = "Technology")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
) {
    Scaffold(
        topBar = {
            TopBar1(
                modifier = Modifier,
                title = stringResource(R.string.categories),
                description = stringResource(R.string.thousands_of_articles_in_each_category)
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .padding(defaultPadding)
                    .fillMaxSize(),
            ) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding()
                        .fillMaxWidth(),
                    columns = GridCells.Adaptive(minSize = 152.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ){
                    items(categoriesList) { category ->
                        CategoryCard (
                            categoryItem = category,
                            onClick = { item ->
                                homeViewModel.updateCategory(item)
                                navController.navigate(Route.HomeScreen.route)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    NuntiumTheme {

    }
}