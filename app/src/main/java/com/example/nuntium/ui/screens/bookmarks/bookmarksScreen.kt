package com.example.nuntium.ui.screens.bookmarks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nuntium.R
import com.example.nuntium.data.model.DataTest
import com.example.nuntium.ui.common.BottomBar
import com.example.nuntium.ui.common.TopBarComponent
import com.example.nuntium.ui.common.defaultPadding
import com.example.nuntium.ui.theme.NuntiumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksScreen() {
    Scaffold(
        topBar = {
            TopBarComponent(
                title = stringResource(R.string.bookmarks),
                desctiption = stringResource(R.string.saved_articles_to_the_library)
            )
        },
        bottomBar = {
            BottomBar {

            }
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
                BookmarkedList(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .fillMaxSize(),
                    newsData = DataTest,
                    onNewsClick = { }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookmarksScreenPreview() {
    NuntiumTheme {
        BookmarksScreen()
    }
}