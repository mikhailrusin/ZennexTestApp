package com.mikhailrusin.zennextestapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mikhailrusin.zennextestapp.data.network.NewsApi
import com.mikhailrusin.zennextestapp.data.network.NewsItem
import com.mikhailrusin.zennextestapp.data.network.NewsPagingSource
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(private val newsApi: NewsApi): Repository {

    override fun fetchNews(): Flow<PagingData<NewsItem>> {
        val config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 40,
            prefetchDistance = 5
        )

        return Pager(
            config = config,
            pagingSourceFactory = { NewsPagingSource(newsApi) }
        ).flow
    }
}