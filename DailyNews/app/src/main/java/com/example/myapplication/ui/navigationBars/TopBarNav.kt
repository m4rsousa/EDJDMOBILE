package com.example.myapplication.ui.navigationBars

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.myapplication.enums.NewsCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNav(
    newsCategory: NewsCategory
) {
    val title = when (newsCategory) {
        NewsCategory.LATEST -> "Latest"
        NewsCategory.HEALTH -> "Health"
        NewsCategory.POLITICS -> "Politics"
        NewsCategory.SPORTS -> "Sports"
    }

    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        title = {
            Text(
                text = title,
                fontSize = 30.sp,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = Color(0xFF221931)
        )
    )
}