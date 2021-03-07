package com.mikhailrusin.zennextestapp.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mikhailrusin.zennextestapp.R

@BindingAdapter("bindImage")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load(imgUrl)
            .placeholder(R.drawable.placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imgView)
    }
}