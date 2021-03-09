package com.mikhailrusin.zennextestapp.ui.news_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mikhailrusin.zennextestapp.R
import com.mikhailrusin.zennextestapp.databinding.LoadStateItemBinding
class LoadingStateAdapter(
    private val errorMessage: String,
    private val retry: () -> Unit
) : LoadStateAdapter<LoadingStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.load_state_item, parent, false)
        val binding = LoadStateItemBinding.bind(view)
        return LoadStateViewHolder(errorMessage, binding, retry)
    }

    override fun onBindViewHolder(
            holder: LoadStateViewHolder,
            loadState: LoadState
    ) = holder.bind(loadState)


    class LoadStateViewHolder(
        private val errorMessage: String,
        binding: LoadStateItemBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private val progressBar: ProgressBar = binding.progressbar
        private val errorMsg: TextView = binding.error
        private val retry: Button = binding.retry
            .also {
                it.setOnClickListener { retry() }
            }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                errorMsg.text = errorMessage
            }

            progressBar.isVisible = loadState is LoadState.Loading
            retry.isVisible = loadState is LoadState.Error
            errorMsg.isVisible = loadState is LoadState.Error
        }
    }
}




