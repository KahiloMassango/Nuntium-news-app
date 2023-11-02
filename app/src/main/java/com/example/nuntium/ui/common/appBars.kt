package com.example.nuntium.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nuntium.R
import com.example.nuntium.ui.theme.NuntiumTheme


data class NavItem(
    @DrawableRes val icon: Int,
    val name: String
)

val NavigationItemIcons = listOf(
    NavItem(R.drawable.home_icon, "Home"),
    NavItem(R.drawable.categories_icon, "Categories"),
    NavItem(R.drawable.bookmark_icon, "Bookmarks"),
    NavItem(R.drawable.profile_icon, "Profile")
)

@Composable
fun BottomBar(
    onClick: () -> Unit
){
    val selectedIndex by remember { mutableIntStateOf(1) }
    Card(
        modifier = Modifier.height(80.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement =  Arrangement.SpaceEvenly
        ) {
            NavigationItemIcons.forEachIndexed { index, navItem ->
                NavBarItem(
                    item = navItem,
                    selected = index == selectedIndex,
                    onClick = {
                        //onClick(navItem.name, index)
                    }
                )
            }
        }
    }
}

@Composable
fun NavBarItem(
    item: NavItem,
    selected: Boolean,
    onClick: (String) -> Unit
) {
    IconButton(
        onClick = {  onClick(item.name)},
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = if(selected) MaterialTheme.colorScheme.primary else
                MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.name,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun TopBarComponent(
    title: String,
    desctiption: String,
) {
    Column(
        modifier = Modifier
            .padding(defaultPadding)
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier,
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            text = desctiption,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.tertiary
        )
    }

}


@Preview(showBackground = true)
@Composable
fun AppBarsPreview() {
    NuntiumTheme {
        TopBarComponent(
            title = "Bookmarks",
            desctiption = "Saved articles to the library"
        )
    }
}

