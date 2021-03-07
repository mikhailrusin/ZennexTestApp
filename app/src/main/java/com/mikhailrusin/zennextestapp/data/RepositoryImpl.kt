package com.mikhailrusin.zennextestapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mikhailrusin.zennextestapp.data.network.ArticleDTO
import com.mikhailrusin.zennextestapp.data.network.NewsApi
import com.mikhailrusin.zennextestapp.data.network.NewsPagingSource
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val newsApi: NewsApi
    ): Repository {

    @ExperimentalPagingApi
    override fun fetchNews(): Flow<PagingData<ArticleDTO>> {
        val config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20,
            prefetchDistance = 5
        )

        return Pager(
            config = config,
            pagingSourceFactory = { NewsPagingSource(newsApi) }
        ).flow
    }
}