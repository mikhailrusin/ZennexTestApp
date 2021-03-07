package com.mikhailrusin.zennextestapp.data

import androidx.paging.PagingData
import com.mikhailrusin.zennextestapp.data.network.ArticleDTO
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun fetchNews(): Flow<PagingData<ArticleDTO>>
}