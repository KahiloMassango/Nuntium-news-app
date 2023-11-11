package com.example.nuntium.ui.screens.language

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.nuntium.ui.commonUi.defaultPadding
import com.example.nuntium.ui.nvgraph.Route
import com.example.nuntium.ui.theme.SfProFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        modifier = Modifier,
                        text = "Language",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Route.PreferenceScreen.route) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(defaultPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SelectableCard(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Portuguese",
                    onClick = {  },
                    selected = true
                )
                SelectableCard(
                    modifier = Modifier.fillMaxWidth(),
                    text = "English",
                    onClick = {  },
                    selected = false
                )
                SelectableCard(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Spanish",
                    onClick = {  },
                    selected = false
                )
                SelectableCard(
                    modifier = Modifier.fillMaxWidth(),
                    text = "German",
                    onClick = {  },
                    selected = false
                )
            }
        }
    }
}




@Composable
fun SelectableCard(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (String) -> Unit,
    selected: Boolean
){
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick(text) },
        colors = CardDefaults.cardColors(
            containerColor = if(selected) MaterialTheme.colorScheme.primary else
                Color(0xFFF3F4F6),
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                fontFamily = SfProFontFamily,
                style = MaterialTheme.typography.bodyMedium,
                color = if(selected) Color.White else Color(0xFF666C8E)
            )
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = if(selected) Color.White else Color(0xFF666C8E)
            )
        }
    }
}