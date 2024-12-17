package com.example.myapplication.ui.navigationBars

import android.content.Intent
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.enums.NewsCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNav(
    newsCategory: NewsCategory,
    navController: NavHostController,
    isArticleDetailScreen: MutableState<Boolean>
) {
    val title = when (newsCategory) {
        NewsCategory.LATEST -> "Latest"
        NewsCategory.HEALTH -> "Health"
        NewsCategory.POLITICS -> "Politics"
        NewsCategory.SPORTS -> "Sports"
        NewsCategory.FAVORITES -> "Favorites"
    }
    val isFavorite = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        title = {
            Text(
                text = title,
                fontSize = 30.sp,
            )
        },
        navigationIcon = {
            if (isArticleDetailScreen.value) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        },
        actions = {
            if (isArticleDetailScreen.value) {
                    IconButton(
                        onClick = {
                                val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                type = "text/plain"
                            }
                            context.startService(sendIntent)
                        }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.share),
                            contentDescription = "Share"
                        )
                    }
                    IconButton(
                        onClick = {
                            scope.launch(Dispatchers.IO) {
                            // AppDatabase.getInstance(context)
                            //     ?.articleDao()
                            //     ?.insert(article!!)
                            }
                        }
                    ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = if (isFavorite.value) R.drawable.filledfavorite else R.drawable.outlinedfavorite
                        ),
                        contentDescription = "Favorite",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = Color(0xFF221931)
        )
    )
}