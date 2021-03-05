package com.mikhailrusin.zennextestapp.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mikhailrusin.zennextestapp.data.Repository
import com.mikhailrusin.zennextestapp.data.network.NewsApi
import com.mikhailrusin.zennextestapp.ui.NewsViewModel
import com.mikhailrusin.zennextestapp.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val appModule = module {
    factory { provideMoshi() }
    factory { provideOkHttpClient() }
    factory { provideRetrofit(get(), get()) }
    single { provideNewsApi(get()) }

    single { Repository(get()) }
    viewModel { NewsViewModel(get()) }
}



fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

fun provideMoshi(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

fun provideOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    return OkHttpClient.Builder()
        .addInterceptor(
            httpLoggingInterceptor.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()
}

fun provideNewsApi(retrofit: Retrofit): NewsApi =
    retrofit.create(NewsApi::class.java)
