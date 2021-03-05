package com.mikhailrusin.zennextestapp.data.network

class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsItem>
)

data class NewsItem(
    val source: SourceDTO,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)

class SourceDTO(
    val id: String?,
    val name: String
)