package com.example.myapplication.ui.ui

import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.ui.ui.themes.Article
var articles = mutableStateOf(listOf<Article>())
    private set
var isLoading = mutableStateOf(false)
var error = mutableStateOf<String?>(null)
class HomeViewModel {


}