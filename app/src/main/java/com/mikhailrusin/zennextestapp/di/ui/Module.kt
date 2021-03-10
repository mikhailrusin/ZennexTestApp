package com.mikhailrusin.zennextestapp.di.ui

import com.mikhailrusin.zennextestapp.data.RepositoryImpl
import com.mikhailrusin.zennextestapp.domain.Repository
import com.mikhailrusin.zennextestapp.ui.news_list.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    single<Repository> { RepositoryImpl(get()) }
    viewModel { NewsViewModel(get()) }
}