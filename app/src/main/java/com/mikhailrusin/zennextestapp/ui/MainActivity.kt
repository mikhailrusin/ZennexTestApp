package com.mikhailrusin.zennextestapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikhailrusin.zennextestapp.R
import com.mikhailrusin.zennextestapp.ui.adapter.NewsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    val newsViewModel: NewsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_news.layoutManager = LinearLayoutManager(this)

        val adapter = NewsAdapter()
        recycler_news.adapter = adapter

        lifecycleScope.launch {
            newsViewModel.fetchNews().distinctUntilChanged().collectLatest {
                Log.i("log", "Получено")
                adapter.submitData(it)
            }
        }
    }
}