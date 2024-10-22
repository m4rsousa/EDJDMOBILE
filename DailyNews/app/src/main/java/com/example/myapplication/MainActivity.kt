package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.ArticleDetail
import com.example.myapplication.ui.HomeView
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController, startDestination = Screen.Home.route) {
                        composable(Screen.Home.route) {
                            HomeView(
                                modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                                navController = navController
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
    object Home : Screen("home")
    object ArticleDetail : Screen("article_detail?url={url}") {
        fun createRoute(url: String?) = "article_detail?url=$url"
    }
}

