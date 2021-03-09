package com.mikhailrusin.zennextestapp.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mikhailrusin.zennextestapp.data.network.dto.ArticleDTO
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(private val newsApi: NewsApi) :
    PagingSource<Int, ArticleDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDTO> {
        val page = params.key ?: 1
        return try {
            val response = newsApi.getNews(page).articles
            LoadResult.Page(
                response, prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleDTO>): Int? {
        return null
    }
}