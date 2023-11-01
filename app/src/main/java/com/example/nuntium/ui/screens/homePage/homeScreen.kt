package com.example.nuntium.ui.screens.homePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nuntium.R
import com.example.nuntium.data.model.Data
import com.example.nuntium.ui.common.BottomBar
import com.example.nuntium.ui.common.CategorySlider
import com.example.nuntium.ui.common.NewsList
import com.example.nuntium.ui.common.SearchContainer
import com.example.nuntium.ui.common.defaultPadding
import com.example.nuntium.ui.screens.category.categoriesList

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
) {
    val uiState by homeViewModel.uiState.collectAsState()

    when(uiState) {
        is HomeUiState.Success -> {
            val successState = uiState as HomeUiState.Success
            SuccessScreen(
                newsList = successState.newsList,
                searchText = homeViewModel.searchText,
                onSearchTextChange = { homeViewModel.updateSearchText(it) },
                onSearch = homeViewModel::getNewsByKeyword,
                onCategoryChange = { category ->
                    homeViewModel.getNewsByCategory(category)
                }
            )
        }
        HomeUiState.Error -> ErrorScreen(retryAction = homeViewModel::getRecommendedNews)
        HomeUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize()) // Handle loading state
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
    newsList: List<Data>,
    searchText: String,
    onSearch: () -> Unit,
    onCategoryChange: (String) -> Unit,
    onSearchTextChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    Scaffold(
        bottomBar = {
            BottomBar { }
        }
    ) { paddingValue ->
        Surface(
            modifier = Modifier.padding(paddingValue),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(defaultPadding)
            ) {
                Text(
                    text = stringResource(R.string.browse),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = stringResource(R.string.discover_things_of_this_world),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
                SearchContainer(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .fillMaxWidth(),
                    searchText = searchText,
                    onValueChange = { onSearchTextChange(it) },
                    onSearch = {
                        focusManager.clearFocus()
                        onSearch()
                    }
                )
                CategorySlider(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(),
                    topicList = categoriesList,
                    onClick =  { onCategoryChange(it) }
                )
                NewsList(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(),
                    news = newsList
                )

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
