package com.mikhailrusin.zennextestapp.data.network.dto

data class ArticleDTO(
    val title: String?,
    val description: String,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String?
)