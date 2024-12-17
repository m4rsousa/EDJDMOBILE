package com.example.myapplication.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.parseDate
import org.json.JSONObject
import java.util.Date

@Entity
data class Article(
    @PrimaryKey
    var id: Int?,
    var title: String?,
    var description: String?,
    var urlToImage: String?,
    var url: String?,
    var publishedAt: Date?,
    var isFavorite: Boolean = false
) {
    companion object{
        fun fromJson(json: JSONObject):Article {
            return Article(
                id = json.getString("id").toInt(),
                title = json.getString("cleanTitle"),
                description = json.getString("descricao"),
                urlToImage = json.getString("multimediaPrincipal"),
                url = json.getString("url"),
                publishedAt = json.getString("data").parseDate() ,
                isFavorite = json.optBoolean("isFavorite", false)
            )
        }
    }

}

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getAll(): List<Article>

    @Query("SELECT * FROM article WHERE url = :url")
    fun loadByUrl(url: String): Article

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( article: Article)

    @Query("SELECT * FROM Article WHERE isFavorite = 1")
    fun getFavoriteArticles(): List<Article>

    @Update
    fun update(article: Article)

    @Delete
    fun delete(article: Article)
}
