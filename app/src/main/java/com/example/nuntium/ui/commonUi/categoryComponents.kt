package com.example.nuntium.ui.commonUi

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nuntium.ui.screens.category.Category
import com.example.nuntium.ui.theme.SfProFontFamily


@Composable
fun CategorySlider(
    modifier: Modifier = Modifier,
    topicList: List<Category>,
    onClick: (String) -> Unit,
    selectedCategory: String
) {
    val category = topicList.filter { it.name == selectedCategory }.first()
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

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    categoryItem: Category,
    onClick: (String) -> Unit,
){
    Card(
        modifier = modifier
            .size(width = 160.dp, height = 72.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick(categoryItem.name) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        border = BorderStroke(1.dp, Color(0x52ACAFC3))
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${categoryItem.emoji} ${categoryItem.name}",
                fontFamily = SfProFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

