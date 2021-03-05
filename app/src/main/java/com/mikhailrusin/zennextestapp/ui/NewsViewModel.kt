package com.mikhailrusin.zennextestapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mikhailrusin.zennextestapp.data.Repository

class NewsViewModel(private val repository: Repository) : ViewModel() {

    fun fetchNews() = repository.fetchNews()
        .cachedIn(viewModelScope)
}