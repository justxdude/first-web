package com.example.plugins

import com.example.managers.ArticlesManager
import com.example.models.Article
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Application.configureRouting(
    articlesManager: ArticlesManager

) {
    routing {
        get("/") {
            call.respondRedirect("articles")
        }
        route("articles") {
            get {
                call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to articlesManager.getAllArticles())))
            }
            get("article_not_found") {
                call.respond(FreeMarkerContent("articleNotFound.ftl", model = null))
            }
            get("new") {
                call.respond(FreeMarkerContent("new.ftl", model = null))
            }
            post {
                val formParameters = call.receiveParameters()
                val title = formParameters.getOrFail("title")
                val body = formParameters.getOrFail("body")
                val article = articlesManager.addArticle(title, body)
                call.respondRedirect("/articles/${article.id}")
            }
            get("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val article = articlesManager.getArticleById(id)
                if(article == null) call.respond(FreeMarkerContent("articleNotFound.ftl", model = null)) else
                call.respond(FreeMarkerContent("show.ftl", mapOf("article" to articlesManager.getArticleById(id))))
            }
            get("{id}/edit") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                call.respond(FreeMarkerContent("edit.ftl", mapOf("article" to articlesManager.getArticleById(id))))
            }
            post("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val formParameters = call.receiveParameters()
                when (formParameters.getOrFail("_action")) {
                    "update" -> {
                        val article = articlesManager.getArticleById(id)
                        if (article != null) {
                            val title = formParameters.getOrFail("title")
                            val body = formParameters.getOrFail("body")

                            article.title = title
                            article.body = body
                            articlesManager.updateArticle(article) // Assuming you have an update method

                            call.respondRedirect("/articles/$id")
                        } else {
                            call.respond(FreeMarkerContent("articleNotFound.ftl", model = null))
                        }
                    }

                    "delete" -> {
                        articlesManager.deleteArticle(id)
                        call.respondRedirect("/articles")
                    }
                }
            }
        }
    }
}
