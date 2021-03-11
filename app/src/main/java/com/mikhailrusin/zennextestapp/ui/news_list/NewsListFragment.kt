package com.mikhailrusin.zennextestapp.ui.news_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikhailrusin.zennextestapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsListFragment : Fragment(R.layout.fragment_news_list) {
    private val newsViewModel: NewsViewModel by viewModel()
    private lateinit var adapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_news.layoutManager = LinearLayoutManager(context)

        val collageClickListener = NewsClickListener {
            val bundle = Bundle().apply {
                putString("url", it)
            }
            findNavController().navigate(R.id.newsOverviewFragment, bundle)
        }

        adapter = NewsAdapter(collageClickListener)
        recycler_news.adapter = adapter.withLoadStateFooter(
            LoadingStateAdapter(
                resources.getString(R.string.connection_error),
                adapter::retry)
        )
        newsViewModel.fetchNews().observe(viewLifecycleOwner, {
            viewLifecycleOwner.lifecycleScope.launch {
                it?.let { adapter.submitData(it) }
            }
        })
        swipe_refresh.setOnRefreshListener {
            refreshNews()
        }
    }

    override fun onStart() {
        super.onStart()
        activity?.toolbar?.visibility = View.VISIBLE
    }

    fun refreshNews() {
        swipe_refresh.isRefreshing = true
        adapter.refresh()
        swipe_refresh.isRefreshing = false
    }
}