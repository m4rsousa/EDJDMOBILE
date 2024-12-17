package com.example.myapplication.repositories

import com.example.myapplication.models.Article
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ArticleAPI {
    suspend fun fetchArticle(category: String): List<Article> {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://www.publico.pt/api/list/$category")
            .build()

        val response =  client.newCall(request).await()

        if (!response.isSuccessful) throw IOException("Unexpected code $response")

        val result = response.body!!.string()
        val jsonResult = JSONArray(result)
        val articlesList = mutableListOf<Article>()
        for (i in 0 until jsonResult.length()) {
            val articleJson = jsonResult.getJSONObject(i)
            val article = Article.fromJson(articleJson)
            articlesList.add(article) // Add article to the list
        }
        return articlesList // Update the articles state variable
    }
}

suspend fun Call.await(recordStack: Boolean = false): Response {
    val callStack = if (recordStack) {
        IOException().apply {
            // Remove unnecessary lines from stacktrace
            // This doesn't remove await$default, but better than nothing
            stackTrace = stackTrace.copyOfRange(1, stackTrace.size)
        }
    } else {
        null
    }
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                continuation.resume(response)
            }

            override fun onFailure(call: Call, e: IOException) {
                // Don't bother with resuming the continuation if it is already cancelled.
                if (continuation.isCancelled) return
                callStack?.initCause(e)
                continuation.resumeWithException(callStack ?: e)
            }
        })

        continuation.invokeOnCancellation {
            try {
                cancel()
            } catch (ex: Throwable) {
                //Ignore cancel exception
            }
        }
    }
}