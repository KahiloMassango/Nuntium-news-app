package com.example.nuntium.ui.screens.selectTopics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nuntium.R
import com.example.nuntium.ui.common.CustomButton
import com.example.nuntium.ui.common.defaultPadding
import com.example.nuntium.ui.theme.NuntiumTheme
import com.example.nuntium.ui.theme.SfProFontFamily
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

const val TAG = "topic"
data class Topic(
    val emoji:String,
    val name: String
)

val topicList = mutableListOf<Topic>(
    Topic("\uD83C\uDFC8", name = "Sports"),
    Topic("⚖️", name = "Politics"),
    Topic("\uD83C\uDF1E", name = "Life"),
    Topic("\uD83C\uDFAE", name = "Gaming"),
    Topic("\uD83D\uDC3B", name = "Animals"),
    Topic("\uD83C\uDF34", name = "Nature"),
    Topic("\uD83C\uDF54", name = "Food"),
    Topic("\uD83C\uDFA8", name = "Art"),
    Topic("\uD83D\uDCDC", name = "History"),
    Topic("\uD83D\uDC57", name = "Fashion")
)


@Composable
fun SelectTopicsScreen() {
    var selectedTopics  = remember { mutableStateListOf<String>() }
    Column(
        modifier = Modifier
            .padding(defaultPadding)
            .fillMaxSize(),
    ) {
        Text(
            modifier = Modifier,
            text = stringResource(R.string.select_your_favorite_topics),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            text = stringResource(R.string.select_favorite_topics_info),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.tertiary
        )

        LazyVerticalGrid(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(),
            columns = GridCells.Adaptive(minSize = 152.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(topicList) { topic ->
                SelectableTopicCard(
                    modifier = Modifier,
                    topic = topic,
                    selected = topic.name in selectedTopics,
                    onClick = {
                        if (topic.name in selectedTopics){
                            selectedTopics.remove(topic.name)
                        } else {
                            selectedTopics.add(topic.name)
                        }
                    }
                )
            }
        }
        CustomButton(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            text = stringResource(id = R.string.next),
            onClick = { /* TODO */ }
        )
    }
}

@Composable
fun SelectableTopicCard(
    modifier: Modifier = Modifier,
    topic: Topic,
    onClick: (String) -> Unit,
    selected: Boolean
){
    Card(
        modifier = modifier
            .size(width = 160.dp, height = 72.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick(topic.name) },
        colors = CardDefaults.cardColors(
            containerColor = if(selected) MaterialTheme.colorScheme.primary else
                MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${topic.emoji} ${topic.name}",
                fontFamily = SfProFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                color = if (selected) MaterialTheme.colorScheme.background else
                    MaterialTheme.colorScheme.tertiary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectTopicsPreview() {
    NuntiumTheme {
        SelectTopicsScreen()
       // val topic = Topic("⚖️", name = "Politics")
    }
}
