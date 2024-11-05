package com.example.myapplication.ui

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.Screen
import com.example.myapplication.models.Article
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.text.DateFormat
import java.util.Date
import java.util.Locale


@Composable
fun ArticleRow( article: Article,navController: NavHostController) {

    Row(modifier = Modifier.padding(6.dp)
        .fillMaxWidth()
        .background( Color(0xFF36323D))
        .clickable { navController.navigate(Screen.ArticleDetail.createRoute(article.url)) },
        verticalAlignment = Alignment.CenterVertically)
    {
        Column ( modifier = Modifier
            .padding(10.dp),
            verticalArrangement = Arrangement.Bottom){

            Text(text = article.publishedAt?.let {
                val dateFormat = DateFormat.getDateTimeInstance(
                    DateFormat.MEDIUM,
                    DateFormat.SHORT,
                    Locale.getDefault()
                )
                dateFormat.format(it)
            } ?: "")
            Text(text = article.title?:"vazio",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif)
            article.urlToImage?.let {
                AsyncImage(
                    model = it,
                    contentDescription = "descricao imagem",
                    modifier = Modifier.padding(vertical= 15.dp).fillMaxWidth())
            } ?: run {
                Image(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    painter = painterResource(id = R.drawable.baseline_image_search_24),
                    contentDescription = "image article"
                )}
            Text(text = article.description!!,
                fontSize = 18.sp)
        }
    }
}


@Preview(showBackground=true)
@Composable
fun ArticleRowPreview(){
    MyApplicationTheme(){
        var article = Article(
            "Title",
            "description",
            "multimedia",
            "url",
            Date()
        )
    }
}
