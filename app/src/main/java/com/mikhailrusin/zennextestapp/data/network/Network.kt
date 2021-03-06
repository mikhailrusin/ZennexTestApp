package com.mikhailrusin.zennextestapp.data.network

import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "26eddb253e7840f988aec61f2ece2907"

interface NewsApi {
    @GET("v2/everything?q=ios&from=2019-04-00&sortBy=publishedAt&apiKey=$API_KEY")
    suspend fun getNews(
        @Query("page") page: Int,
    ): NewsResponse
}