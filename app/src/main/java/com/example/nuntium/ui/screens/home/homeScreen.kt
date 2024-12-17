package com.example.nuntium.ui.screens.home

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.nuntium.R
import com.example.nuntium.ui.commonUi.CategorySlider
import com.example.nuntium.ui.commonUi.NewsList
import com.example.nuntium.ui.commonUi.SearchContainer
import com.example.nuntium.ui.commonUi.TopBar
import com.example.nuntium.ui.commonUi.defaultPadding
import com.example.nuntium.ui.nvgraph.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val voiceToTextParser = VoiceToTextParser(context)
    var canRecord by remember { mutableStateOf(false) }
    val recordAudioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            canRecord = isGranted
        }
    )

    LaunchedEffect(key1 = recordAudioLauncher) {
        recordAudioLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    val state by voiceToTextParser.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                title = stringResource(R.string.browse),
                description = stringResource(R.string.discover_things_of_this_world),
                scrollBehavior = scrollBehavior
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
                    searchText = viewModel.searchText,
                    onValueChange = { viewModel.updateSearchText(it) },
                    onSearch = {
                        viewModel.searchNewsByKeyword()
                        focusManager.clearFocus()
                    },
                    onMicClick = {
                        if(state.isSpeaking){
                            voiceToTextParser.stopListening()
                            viewModel.updateSearchText("")
                        } else {
                            voiceToTextParser.startListening()
                            viewModel.updateSearchText("Listening...")
                        }
                    }
                )
                LaunchedEffect(key1 = state.spokenText){
                    viewModel.updateSearchText(state.spokenText)
                    viewModel.searchNewsByKeyword()
                }
                CategorySlider(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(),
                    selectedCategory = viewModel.selectedCategory,
                    onClick =  { viewModel.updateCategory(it) }
                )
                if (uiState == null){
                    Column(
                        modifier  = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Parece que está offline, vá para notícias salvas")
                    }
                } else {
                    NewsList(
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth(),
                        articles = uiState!!,
                        onFavorite = { viewModel.saveArticleLocally(it) },
                        onArticleCLick = { article ->
                            viewModel.setArticle(article)
                            navController.navigate(Route.ArticleScreen.route)
                        }
                    )
                }
            }
        }
    }
}


