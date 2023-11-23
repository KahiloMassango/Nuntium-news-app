package com.example.nuntium.ui.commonUi

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

data class Category(val name: String)

val categoriesList = listOf(
    Category( name = "General"),
    Category( name = "Sports"),
    Category(name = "Business"),
    Category( name = "Entertainment"),
    Category(name = "Science"),
    Category( name = "Technology")
)

@Composable
fun CategorySlider(
    modifier: Modifier = Modifier,
    topicList: List<Category> = categoriesList,
    onClick: (String) -> Unit,
    selectedCategory: String
) {
    val category = topicList.first { it.name == selectedCategory }
    val index = topicList.indexOf(category)
    val state = rememberLazyListState()

    LaunchedEffect(key1 = index){
        state.animateScrollToItem(index)
    }
    LazyRow(
        state = state,
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        topicList.forEach { topic ->
            item {
                CategoryItem(
                    topic = topic,
                    selected = topic.name == selectedCategory,
                    onClick = { category ->
                        onClick(category)

                    }
                )
            }
        }
    }

}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    topic: Category,
    selected: Boolean,
    onClick: (String) -> Unit
) {
    val cardAnimatedColor by animateColorAsState(
        if(selected) MaterialTheme.colorScheme.primary else
            MaterialTheme.colorScheme.surfaceVariant,
        animationSpec = tween(300, easing = LinearEasing),
        label = ""
    )
    val textColor by animateColorAsState(
        if (selected) MaterialTheme.colorScheme.background else
            MaterialTheme.colorScheme.tertiary,
        animationSpec = tween(300, easing = LinearEasing),
        label = ""
    )
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .clickable { onClick(topic.name) },
        colors = CardDefaults.cardColors(
            containerColor = cardAnimatedColor,
        )
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            text = topic.name,
            style = MaterialTheme.typography.bodyMedium,
            color = textColor,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

