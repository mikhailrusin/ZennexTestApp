package com.mikhailrusin.zennextestapp.ui.news_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mikhailrusin.zennextestapp.databinding.NewsItemBinding
import com.mikhailrusin.zennextestapp.domain.DomainNews

class NewsAdapter(
    private val clickListener: NewsClickListener
) : PagingDataAdapter<DomainNews, NewsAdapter.NewsViewHolder>(NewsComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent, clickListener)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    class NewsViewHolder private constructor(
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
            fun from(parent: ViewGroup, clickListener: NewsClickListener): NewsViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = NewsItemBinding.inflate(inflater, parent, false)
                return NewsViewHolder(binding, clickListener)
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