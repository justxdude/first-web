package com.example.managers

import com.example.models.Article
import java.util.concurrent.atomic.AtomicInteger

class ArticlesManager {
    private val idCounter = AtomicInteger()
    var articles: MutableList<Article> = mutableListOf()

    init {
        articles = mutableListOf(
            Article(
                idCounter.incrementAndGet(),
                "Hello World!",
                "Hello, this is my first article, nice to see you!"
            )
        )
    }

    fun getAllArticles(): List<Article> = articles

    fun getArticleById(id: Int): Article? = articles.firstOrNull { it.id == id }

    fun addArticle(title: String, body: String): Article {
        val id = idCounter.incrementAndGet()
        val newArticle = Article(id, title, body)
        articles.add(newArticle)
        return newArticle
    }

    fun updateArticle(article: Article) {
        val articleIndex = articles.indexOfFirst { it.id == article.id }
        if (articleIndex != -1) {
            articles[articleIndex] = article
        }
    }

    fun deleteArticle(id: Int) {
        articles.removeIf { it.id == id }
    }
}