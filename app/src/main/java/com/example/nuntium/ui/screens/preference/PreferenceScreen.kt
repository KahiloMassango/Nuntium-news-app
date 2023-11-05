package com.example.nuntium.ui.screens.preference

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.nuntium.ui.common.BottomBar
import com.example.nuntium.ui.common.TopBar1
import com.example.nuntium.ui.common.defaultPadding
import com.example.nuntium.ui.nvgraph.Route
import com.example.nuntium.ui.theme.NuntiumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferenceScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopBar1(
                modifier = Modifier
                    .fillMaxWidth(),
                title = "Preferences",
            )
        },
        bottomBar = {
            BottomBar(navController)
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .padding(defaultPadding)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(0.dp))
                PreferenceOption(
                    option = "Language",
                    onClick = { navController.navigate(Route.LanguageScreen.route) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                PreferenceOption(
                    option = "Privacy",
                    onClick = { }
                )
                Spacer(modifier = Modifier.height(16.dp))
                PreferenceOption(
                    option = "Terms & Conditions",
                    onClick = { }
                )
            }
        }
    }
}

@Composable
fun PreferenceOption(
    option: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF3F4F6)
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = option,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArticlePreview() {
    NuntiumTheme {

    }
}