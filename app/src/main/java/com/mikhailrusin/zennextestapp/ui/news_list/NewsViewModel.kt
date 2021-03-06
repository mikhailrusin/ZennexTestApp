package com.mikhailrusin.zennextestapp.ui.news_list

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.mikhailrusin.zennextestapp.domain.DomainNews
import com.mikhailrusin.zennextestapp.domain.Repository
import com.mikhailrusin.zennextestapp.util.toDomainNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map

class NewsViewModel(private val repository: Repository) : ViewModel() {
    val isLoading = MutableLiveData(false)

    fun fetchNews(): LiveData<PagingData<DomainNews>> {
        isLoading.value = true
        return repository.fetchNews()
            .map { it.map { it.toDomainNews() } }
            .asLiveData(Dispatchers.Default)
            .cachedIn(viewModelScope)
    }
}