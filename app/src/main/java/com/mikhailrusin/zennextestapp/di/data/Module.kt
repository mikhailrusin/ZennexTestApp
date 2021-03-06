package com.mikhailrusin.zennextestapp.di.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mikhailrusin.zennextestapp.data.network.NewsApi
import com.mikhailrusin.zennextestapp.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {
    factory { provideMoshi() }
    factory { provideOkHttpClient() }
    factory { provideRetrofit(get(), get()) }
    single { provideNewsApi(get()) }
}

private fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

private fun provideMoshi(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private fun provideOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    return OkHttpClient.Builder()
        .addInterceptor(
            httpLoggingInterceptor.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()
}

private fun provideNewsApi(retrofit: Retrofit): NewsApi =
    retrofit.create(NewsApi::class.java)