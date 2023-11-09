package com.example.nuntium.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.nuntium.R
import com.example.nuntium.data.model.Article
import com.example.nuntium.ui.common.CategorySlider
import com.example.nuntium.ui.common.NewsList
import com.example.nuntium.ui.common.SearchContainer
import com.example.nuntium.ui.common.TopBar1
import com.example.nuntium.ui.common.defaultPadding
import com.example.nuntium.ui.screens.category.categoriesList

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    navController: NavHostController
) {
    val uiState by homeViewModel.uiState.collectAsState()

    when(uiState) {
        is HomeUiState.Success -> {
            val successState = uiState as HomeUiState.Success
            SuccessScreen(
                newsList = successState.newsList,
                searchText = homeViewModel.searchText,
                selectedCategory = homeViewModel.selectedCategory,
                onSearchTextChange = { homeViewModel.updateSearchText(it) },
                onSearch = homeViewModel::getNewsByKeyword,
                onCategoryChange = { category -> homeViewModel.updateCategory(category) },
                onFavorite = { homeViewModel.addToFavorite(it) },
                navController = navController
            )
        }
        HomeUiState.Error -> ErrorScreen(retryAction = homeViewModel::getRecommendedNews)
       // HomeUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize()) // Handle loading state
    }
}


@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "loading failed", modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text("retry")
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
    newsList: List<Article>?,
    searchText: String,
    onSearch: () -> Unit,
    selectedCategory: String,
    onFavorite: (Article) -> Unit,
    onCategoryChange: (String) -> Unit,
    onSearchTextChange: (String) -> Unit,
    navController: NavHostController
) {
    val focusManager = LocalFocusManager.current
    Scaffold(
        topBar = {
            TopBar1(
                modifier = Modifier,
                title = stringResource(R.string.browse),
                description = stringResource(R.string.discover_things_of_this_world)
            )
        }
    ) { paddingValue ->
        Surface(
            modifier = modifier.padding(paddingValue),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(defaultPadding)
            ) {
                SearchContainer(
                    modifier = Modifier
                        .fillMaxWidth(),
                    searchText = searchText,
                    onValueChange = { onSearchTextChange(it) },
                    onSearch = {
                        onSearch()
                        focusManager.clearFocus()
                    }
                )
                CategorySlider(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(),
                    topicList = categoriesList,
                    selectedCategory = selectedCategory,
                    onClick =  { onCategoryChange(it) }
                )
                if (newsList == null){
                    Column(
                        modifier  = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "It appears you are offline! goto to saved articles")
                    }
                } else {
                    NewsList(
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth(),
                        news = newsList,
                        onFavorite = { onFavorite(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = "Loading"
    )
}
