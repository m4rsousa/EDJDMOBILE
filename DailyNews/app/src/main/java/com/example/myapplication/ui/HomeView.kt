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
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.models.Article
import com.example.myapplication.ui.theme.MyApplicationTheme
import okhttp3.OkHttpClient
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import org.json.JSONArray

@Composable
fun HomeView( modifier: Modifier = Modifier) {

   var articles by remember { mutableStateOf(listOf<Article>())}
    LazyColumn {
        itemsIndexed(items=articles){ index,article ->
            ArticleRow(article=article)
        }
    }
    LaunchedEffect(Unit){
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://www.publico.pt/api/list/ultimas")
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


@Preview(showBackground=true)
@Composable
fun HomeViewPreview(){
    MyApplicationTheme(){
        HomeView()
    }
}











