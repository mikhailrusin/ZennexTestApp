package com.mikhailrusin.zennextestapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mikhailrusin.zennextestapp.data.network.NewsItem
import com.mikhailrusin.zennextestapp.databinding.NewsItemBinding

class NewsAdapter : PagingDataAdapter<NewsItem, NewsAdapter.GameViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class GameViewHolder private constructor(
        private val binding: NewsItemBinding
    )
        : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): GameViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = NewsItemBinding.inflate(inflater, parent, false)
                return GameViewHolder(binding)
            }
        }

        fun bind(news: NewsItem) {
            binding.news = news
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<NewsItem>() {
            override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean =
                oldItem == newItem
        }
    }
}