import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.nuntium.data.model.Article
import com.example.nuntium.ui.commonUi.ArticleTopBar
import com.example.nuntium.ui.nvgraph.Route
import com.example.nuntium.ui.screens.article.ArticleViewModel
import com.example.nuntium.ui.screens.home.HomeViewModel
import com.example.nuntium.ui.theme.NuntiumTheme
import com.example.nuntium.ui.theme.SfProFontFamily

@Composable
fun ArticleScreen(
    viewModel: ArticleViewModel = viewModel(factory = HomeViewModel.Factory),
    navController: NavHostController,
    article: Article?
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding()
        ) {
            AsyncImage(
                model = article?.urlToImage ?: "",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(384.dp)
                    .fillMaxWidth()
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = size.height / 3,
                            endY = size.height
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, alpha = 5f, blendMode = BlendMode.Multiply)
                        }
                    }
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                ArticleTopBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, top = 10.dp, end = 10.dp),
                    onNavigateUp = {
                        navController.navigate(Route.HomeScreen.route)
                                   },
                    onBookmark = { /* TODO */ },
                    onOpenInBrowser = {
                        openInBrowser(context, article?.url ?: "")
                    }
                )
                Spacer(modifier = Modifier.height(96.dp))
                Card(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = article?.source?.name ?: "",
                        color = MaterialTheme.colorScheme.background,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600,
                        fontFamily = SfProFontFamily
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    text = article?.title ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier
                        .weight(1f),
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.elevatedCardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 20.dp, end = 20.dp, top = 24.dp)
                    ) {
                        Text(
                            text = "Results",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = article?.content ?: "",
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontFamily = SfProFontFamily,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF666C8E)
                        )
                    }
                }
            }
        }
    }
}

private fun openInBrowser(context: Context, url: String) {
    val webpage: Uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, webpage)
    context.startActivity(intent)
}


@Preview(showBackground = true)
@Composable
fun ArticlePreview() {
    NuntiumTheme {

    }
}




