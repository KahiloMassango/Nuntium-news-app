package com.example.nuntium.ui.screens.homePage

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nuntium.R
import com.example.nuntium.ui.common.BigNewsSlider
import com.example.nuntium.ui.common.CategorySlider
import com.example.nuntium.ui.common.SearchContainer
import com.example.nuntium.ui.common.SmallNewsList
import com.example.nuntium.ui.common.defaultPadding
import com.example.nuntium.ui.screens.selectTopics.topicList
import com.example.nuntium.ui.theme.NuntiumTheme

@Composable
fun HomeScreen() {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding)
            .scrollable(rememberScrollState(), Orientation.Vertical)
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
            searchText = "",
            onValueChange = { /* TODO */ },
            onSearch = { /* TODO */ }
        )
        CategorySlider(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            topicList = topicList,
            onClick =  { /* TODO */ }
        )
        BigNewsSlider(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .padding(top = 48.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.recommended_for_you),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        SmallNewsList(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NuntiumTheme {
        HomeScreen()
    }
}