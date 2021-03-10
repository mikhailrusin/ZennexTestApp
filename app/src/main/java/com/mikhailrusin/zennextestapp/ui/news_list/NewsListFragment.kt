package com.mikhailrusin.zennextestapp.ui.news_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikhailrusin.zennextestapp.R
import com.mikhailrusin.zennextestapp.ui.news_overview.NewsOverviewFragment
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsListFragment : Fragment(R.layout.fragment_news_list) {
    private val newsViewModel: NewsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_news.layoutManager = LinearLayoutManager(context)

        val collageClickListener = NewsClickListener {
            val bundle = Bundle().apply {
                putString("url", it)
            }
            val tag = this.tag
            activity?.supportFragmentManager?.commit {
                addToBackStack(tag)
                replace(R.id.fragment_container, NewsOverviewFragment::class.java, bundle)
            }
        }

        val adapter = NewsAdapter(collageClickListener)
        recycler_news.adapter = adapter.withLoadStateFooter(
            LoadingStateAdapter(
                resources.getString(R.string.connection_error),
                adapter::retry)
        )
        newsViewModel.fetchNews().observe(viewLifecycleOwner, {
            viewLifecycleOwner.lifecycleScope.launch {
                adapter.submitData(it)
            }
        })
    }
}