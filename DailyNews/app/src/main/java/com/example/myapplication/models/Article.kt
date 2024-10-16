package com.example.myapplication.models

import com.example.myapplication.parseDate
import org.json.JSONObject
import java.util.Date

class Article(var title: String?=null,
              var description: String?=null,
              var urlToImage: String?=null,
              var url: String?=null,
              var publishedAt: Date?=null) {

    companion object{
        fun fromJson(json: JSONObject):Article {
            return Article(
                title = json.getString("titulo"),
                description = json.getString("descricao"),
                urlToImage = json.getString("multimediaPrincipal"),
                url = json.getString("url"),
                publishedAt = json.getString("data").parseDate()
            )
        }
    }
}
