package com.example.myapplication.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.Screen
import com.example.myapplication.enums.NewsCategory
import com.example.myapplication.models.Article
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import org.json.JSONArray

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    category: NewsCategory,
) {

    var articles by remember { mutableStateOf(listOf<Article>()) }
    val currentRoute = currentRoute(navController) // Get current route

    LazyColumn() {
        itemsIndexed(items = articles) { index, article ->
            ArticleRow(article = article, navController)
        }
    }
    LaunchedEffect(Unit) {
        val client = OkHttpClient()
        val tag = when (category) {
            NewsCategory.LATEST -> "ultimas"
            NewsCategory.HEALTH -> "saude"
            NewsCategory.POLITICS -> "politica"
            NewsCategory.SPORTS -> "desporto"
            NewsCategory.FAVORITES -> "favoritos"
        }

        val request = Request.Builder()
            .url("https://www.publico.pt/api/list/$tag")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val result = response.body!!.string()
                    val jsonResult = JSONArray(result)
                    val articlesList = mutableListOf<Article>() // Create a list outside the loop
                    for (i in 0 until jsonResult.length()) {
                        val articleJson = jsonResult.getJSONObject(i)
                        val article = Article.fromJson(articleJson)
                        articlesList.add(article) // Add article to the list
                    }
                    articles = articlesList // Update the articles state variable
                }
            }
        })
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route ?: Screen.Home.route
}








