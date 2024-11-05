package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.enums.NewsCategory
import com.example.myapplication.ui.ArticleDetail
import com.example.myapplication.ui.HomeView
import com.example.myapplication.ui.navigationBars.BottomBarNav
import com.example.myapplication.ui.navigationBars.TopBarNav
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                val currentNewsCategory = remember { mutableStateOf(NewsCategory.LATEST) }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopBarNav(currentNewsCategory.value) },
                    bottomBar = { BottomBarNav(navController) }
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController =  navController,
                        startDestination = Screen.Latest.route
                    ) {
                        composable(Screen.Home.route) { backStackEntry ->
                            val categoryArg = backStackEntry.arguments?.getString("category")?.uppercase()
                            currentNewsCategory.value = NewsCategory.valueOf(categoryArg ?: "LATEST")
                            HomeView(
                                navController = navController,
                                category = currentNewsCategory.value
                            )
                        }
                        composable(Screen.ArticleDetail.route) { backStackEntry ->
                            val url = backStackEntry.arguments?.getString("url")
                            if (!url.isNullOrEmpty()) {
                                ArticleDetail(url)
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home/{category}")
    object Latest : Screen("home/latest")
    object Health : Screen("home/health")
    object Politics : Screen("home/politics")
    object Sports : Screen("home/sports")
    object ArticleDetail : Screen("article_detail?url={url}") {
        fun createRoute(url: String?) = "article_detail?url=$url"
    }
}

