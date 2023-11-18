package com.example.nuntium.ui.screens.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.nuntium.R
import com.example.nuntium.ui.commonUi.TopBar1
import com.example.nuntium.ui.commonUi.defaultPadding
import com.example.nuntium.ui.nvgraph.Route
import com.example.nuntium.ui.theme.NuntiumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    navController: NavHostController,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val uiState by viewModel.favoritesUiState.collectAsState()

    Scaffold(
        topBar = {
            TopBar1(
                modifier = Modifier,
                title = stringResource(R.string.bookmarks),
                description = stringResource(R.string.saved_articles_to_the_library)
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
                FavoriteArticleList(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 8.dp),
                    articles = uiState.articles,
                    onNewsClick = { articleDto ->
                        viewModel.setArticle(articleDto)
                        navController.navigate(Route.ArticleScreen.route)
                    },
                    onDelete = { viewModel.deleteArticle(it) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookmarksScreenPreview() {
    NuntiumTheme {

    }
}