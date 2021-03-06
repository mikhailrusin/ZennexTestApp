package com.mikhailrusin.zennextestapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikhailrusin.zennextestapp.R
import com.mikhailrusin.zennextestapp.ui.adapter.NewsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val newsViewModel: NewsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_news.layoutManager = LinearLayoutManager(this)
        val adapter = NewsAdapter()
        recycler_news.adapter = adapter
        lifecycleScope.launch {
            newsViewModel.fetchNews().distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
            adapter.loadStateFlow.collectLatest { loadStated ->
                progressbar.isVisible = loadStated.refresh is LoadState.Loading
                retry.isVisible = loadStated.refresh !is LoadState.Loading
                error.isVisible = loadStated.refresh is LoadState.Error
            }
        }
    }
}