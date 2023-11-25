package com.example.nuntium.ui.screens.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nuntium.R
import com.example.nuntium.data.model.ArticleDto
import com.example.nuntium.ui.theme.NuntiumTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteArticleList(
    modifier: Modifier = Modifier,
    articles: List<ArticleDto>,
    onNewsClick: (ArticleDto) -> Unit,
    onDelete: (ArticleDto) -> Unit
) {
    if (articles.isEmpty()){
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
            items(articles) { article ->
                FavoriteArticleItem(
                    modifier = Modifier.animateItemPlacement(),
                    article = article,
                    onClick = { onNewsClick(it) },
                    onDelete = { onDelete(it) }
                )
            }
        }
    }

}

@Composable
fun FavoriteArticleItem(
    modifier: Modifier = Modifier,
    article: ArticleDto,
    onClick: (ArticleDto) -> Unit,
    onDelete: (ArticleDto) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick(article) }
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = article.urlToImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .size(96.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = article.source,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary,
                    maxLines = 1
                )
                Text(
                    modifier = Modifier,
                    text = article.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 2
                    )
                }
            IconButton(onClick = { onDelete(article) }) {
                Icon(
                    modifier = Modifier.weight(1f),
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookmarksComponentsPreview() {
    NuntiumTheme {

    }
}

