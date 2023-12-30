package com.example.models

import java.util.concurrent.atomic.AtomicInteger
import io.ktor.resources.*

@Resource("/articles")
class Article(
    val id: Int,
    var title: String,
    var body: String,
    val creationTime: Long = System.currentTimeMillis(),
    var lastEditTime: Long = 0
)
{

}