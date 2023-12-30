package com.example

import com.example.managers.ArticlesManager
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.example.plugins.configureTemplating
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*


fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val articlesManager = ArticlesManager()
    configureSerialization()
    configureRouting(articlesManager)
    configureTemplating()
}
