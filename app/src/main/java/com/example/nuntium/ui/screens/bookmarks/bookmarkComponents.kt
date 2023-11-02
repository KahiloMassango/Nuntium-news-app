package com.example.nuntium.ui.screens.bookmarks

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nuntium.R
import com.example.nuntium.data.model.News
import com.example.nuntium.ui.theme.NuntiumTheme


@Composable
fun BookmarkedList(
    modifier: Modifier = Modifier,
    newsData: List<News>,
    onNewsClick: () -> Unit
) {
    if (newsData.isEmpty()){
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.CollectionsBookmark,
                contentDescription = null,
                tint = Color(0xFF6563FF),
                modifier = Modifier
                    .drawBehind {
                        drawCircle(
                            color = Color(0xFFEEF0FB),
                            radius = 100f
                           )
                    }
            )

            Text(
                modifier = Modifier.padding(top = 36.dp),
                text = stringResource(R.string.empty_bookmarks_list),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )
        }
    } else {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(newsData) { news ->
                BookmarkItem(
                    news = news,
                    onClick = onNewsClick
                )
            }
        }
    }

}

@Composable
fun BookmarkItem(
    modifier: Modifier = Modifier,
    news: News,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.example),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .size(96.dp)
        )
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = news.category,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.tertiary,
                maxLines = 1
            )
            Text(
                text = news.title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookmarksComponentsPreview() {
    NuntiumTheme {
        BookmarkedList(
            modifier = Modifier.fillMaxWidth(),
            newsData = emptyList(),
            onNewsClick = { }
        )
    }
}

