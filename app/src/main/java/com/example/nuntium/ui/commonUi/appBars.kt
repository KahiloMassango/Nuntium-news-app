package com.example.nuntium.ui.commonUi

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.OpenInBrowser
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.nuntium.R
import com.example.nuntium.ui.nvgraph.Route
import com.example.nuntium.ui.theme.NuntiumTheme


data class NavItem(
    @DrawableRes val icon: Int,
    val route: String
)

val NavigationItemIcons = listOf(
    NavItem(R.drawable.home_icon, Route.HomeScreen.route),
    NavItem(R.drawable.bookmark_icon, Route.SavedScreen.route),
    NavItem(R.drawable.profile_icon, Route.PreferenceScreen.route)
)

@Composable
fun BottomBar(
    navController: NavHostController
){
    val backStack by navController.currentBackStackEntryAsState()
    val currentDestination = backStack?.destination?.route
    Card(
        modifier = Modifier.height(80.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement =  Arrangement.SpaceEvenly
        ) {
            NavigationItemIcons.forEach { navItem ->
                NavBarItem(
                    navItem = navItem,
                    selected = currentDestination == navItem.route,
                    onClick = { route ->
                        navController.navigate(route){
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun NavBarItem(
    navItem: NavItem,
    selected: Boolean,
    onClick: (String) -> Unit
) {
    val animatedColor by animateColorAsState(
        targetValue = if(selected) MaterialTheme.colorScheme.primary else
            MaterialTheme.colorScheme.onSurfaceVariant,
        label = "",

    )
    IconButton(
        onClick = {  onClick(navItem.route)},
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = animatedColor
        )
    ) {
        Icon(
            painter = painterResource(id = navItem.icon),
            contentDescription = navItem.route,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ArticleTopBar(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
    onSave: () -> Unit,
    onOpenInBrowser: () -> Unit
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onNavigateUp) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(onClick = onSave) {
                Icon(
                    imageVector = Icons.Outlined.BookmarkBorder,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            IconButton(onClick = onOpenInBrowser) {
                Icon(
                    imageVector = Icons.Outlined.OpenInBrowser,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
) {
    TopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        title = {
            Column(
                modifier = modifier.padding(top = 10.dp, bottom = 10.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                if (description != null){
                    Text(
                        modifier = Modifier
                            .padding(top = 5.dp),
                        text = description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AppBarsPreview() {
    NuntiumTheme {

    }
}

