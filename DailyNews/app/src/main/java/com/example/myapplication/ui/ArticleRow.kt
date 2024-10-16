package com.example.myapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.models.Article
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.util.Date

@Composable
fun ArticleRow( article: Article) {
    val articleTitleStyle = TextStyle(
        color = Color.Black,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
    Row(modifier = Modifier.padding(4.dp)
        .background(Color.DarkGray))
    {
        article.urlToImage?.let {
            AsyncImage(
                model = it,
                contentDescription = "descricao imagem",
                modifier = Modifier
                    .padding(6.dp)
                    .width(120.dp)
                    .height(120.dp),

            )
        } ?: run {
            Image(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            painter = painterResource(id = R.drawable.baseline_image_search_24),
            contentDescription = "image article"
        )}
        Column ( modifier = Modifier
            .padding(10.dp),
            verticalArrangement = Arrangement.Bottom){

            Text(text = article.title?:"vazio",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
            Text(text = article.description!!)
            Text(Date().toString())
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
