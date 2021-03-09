package com.mikhailrusin.zennextestapp.ui.news_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.mikhailrusin.zennextestapp.domain.Repository
import com.mikhailrusin.zennextestapp.domain.DomainNews
import com.mikhailrusin.zennextestapp.util.toDomainNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class NewsViewModel(private val repository: Repository) : ViewModel() {
    fun fetchNews(): LiveData<PagingData<DomainNews>> = repository.fetchNews()
        .map { it.map { it.toDomainNews() } }
        .asLiveData(Dispatchers.Default)
        .cachedIn(viewModelScope)
}