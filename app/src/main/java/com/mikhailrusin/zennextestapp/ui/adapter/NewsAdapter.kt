package com.mikhailrusin.zennextestapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mikhailrusin.zennextestapp.databinding.NewsItemBinding
import com.mikhailrusin.zennextestapp.domain.DomainNews
import com.mikhailrusin.zennextestapp.ui.news_list.NewsClickListener

class NewsAdapter(
    private val clickListener: NewsClickListener
) : PagingDataAdapter<DomainNews, NewsAdapter.GameViewHolder>(NewsComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder.from(parent, clickListener)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    class GameViewHolder private constructor(
        private val binding: NewsItemBinding,
        private val clickListener: NewsClickListener
    ) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        init {
            binding.newsContainer.setOnClickListener(this)
        }

        fun bind(news: DomainNews) {
            binding.news = news
        }

        override fun onClick(v: View?) {
            binding.news?.url?.let { clickListener.onClick(it) }
        }

        companion object {
            fun from(parent: ViewGroup, clickListener: NewsClickListener): GameViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = NewsItemBinding.inflate(inflater, parent, false)
                return GameViewHolder(binding, clickListener)
            }
        }
    }


    companion object {
        private val NewsComparator = object : DiffUtil.ItemCallback<DomainNews>() {
            override fun areItemsTheSame(oldItem: DomainNews, newItem: DomainNews): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: DomainNews, newItem: DomainNews): Boolean =
                oldItem == newItem
        }
    }
}