package com.example.myapplication.ui
import com.example.myapplication.ui.theme.Article
data class ArticlesState (
    val articles: ArrayList<Article> = arrayListOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)
