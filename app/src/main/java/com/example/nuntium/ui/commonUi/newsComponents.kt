package com.example.nuntium.ui.commonUi

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.nuntium.data.model.Article

@Composable
fun NewsList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onFavorite: (Article) -> Unit,
    onArticleCLick: (Article) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        //verticalArrangement = Arrangement.spacedBy()
    ) {
        items(articles) { news ->
            ArticleCard(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                onClick = { article ->
                        onArticleCLick(article)
                },
                onFavorite = { onFavorite(it) },
                article = news
            )
        }
    }
}


@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    onClick: (Article) -> Unit,
    onFavorite: (Article) -> Unit,
    article: Article,

) {
    var isLoading by remember { mutableStateOf(true) }
    var isFavorite by rememberSaveable { mutableStateOf(false) }
    val painter = rememberAsyncImagePainter(
        model = article.urlToImage,
        contentScale = ContentScale.Crop,
        onLoading = { isLoading = true },
        onSuccess = { isLoading = false }

    )
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(enabled = !isLoading) { onClick(article) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(192.dp)
                    .fillMaxWidth()
                    .shimmerEffectNew(isLoading)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    .border(1.dp, Color.LightGray)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        modifier = Modifier
                            .shimmerEffectNew(isLoading)
                            .alpha(if (isLoading) 0f else 1f),
                        text = article.source.name,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .shimmerEffectNew(isLoading)
                                .alpha(if (isLoading) 0f else 1f),
                            text = article.title,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        IconButton(
                            modifier = Modifier,
                            onClick = {
                                onFavorite(article)
                                if (!isFavorite){
                                    isFavorite = true
                                }
                            },
                            enabled = !isLoading
                        ) {
                            Icon(
                                modifier = Modifier
                                    .weight(1f)
                                    .shimmerEffectNew(isLoading)
                                    .alpha(if (isLoading) 0f else 1f)
                                ,
                                imageVector = if(isFavorite) Icons.Outlined.Bookmark else
                                    Icons.Outlined.BookmarkBorder,
                                contentDescription = "Favorite",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            }
        }
    }
}



