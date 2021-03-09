package com.mikhailrusin.zennextestapp.data.network

import com.mikhailrusin.zennextestapp.data.network.dto.NewsResponseDTO
import com.mikhailrusin.zennextestapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {
    @GET("v2/everything?q=ios&from=2019-04-00&sortBy=publishedAt&apiKey=$API_KEY")
    suspend fun getNews(
        @Query("page") page: Int,
    ): NewsResponseDTO
}