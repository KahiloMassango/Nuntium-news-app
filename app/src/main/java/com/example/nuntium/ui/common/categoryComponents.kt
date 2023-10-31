package com.example.nuntium.ui.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.nuntium.ui.screens.selectTopics.Topic


@Composable
fun CategorySlider(
    modifier: Modifier = Modifier,
    topicList: List<Topic>,
    onClick: (String) -> Unit
) {
    var selected by rememberSaveable { mutableIntStateOf(0) }
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        topicList.forEachIndexed { index, topic ->
            item {
                CategoryItem(
                    topic = topic,
                    selected = index == selected,
                    onClick = { category ->
                        selected = index
                        onClick(category.lowercase())
                    }
                )
            }
        }
    }

}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    topic: Topic,
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

