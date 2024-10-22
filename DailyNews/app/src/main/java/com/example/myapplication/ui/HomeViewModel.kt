package com.example.myapplication.ui

import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.ui.theme.Article
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

data class ArticlesState (
    val articles: ArrayList<Article> = arrayListOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)
