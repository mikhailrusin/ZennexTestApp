package com.mikhailrusin.zennextestapp.util

import com.mikhailrusin.zennextestapp.data.network.dto.ArticleDTO
import com.mikhailrusin.zennextestapp.domain.DomainNews

fun ArticleDTO.toDomainNews(): DomainNews {
    return DomainNews(
        title,
        description,
        url,
        urlToImage,
        publishedAt
    )
}