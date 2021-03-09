package com.mikhailrusin.zennextestapp.domain

import androidx.paging.PagingData
import com.mikhailrusin.zennextestapp.data.network.dto.ArticleDTO
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun fetchNews(): Flow<PagingData<ArticleDTO>>
}