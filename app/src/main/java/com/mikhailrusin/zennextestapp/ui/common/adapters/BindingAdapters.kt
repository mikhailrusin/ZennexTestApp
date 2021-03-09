package com.mikhailrusin.zennextestapp.ui.common.adapters

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("bindImage")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load(imgUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imgView)
    }
}

/**
 * Parses the string received from server
 * */
@BindingAdapter("bindDate")
fun bindDate(textView: TextView, text: String) {
    val newsDate = text.split("T")
    val date = newsDate[0].split("-").reversed().joinToString("-")
    val time = newsDate[1].split(":")
    textView.text = "$date  ${time[0]}:${time[1]}"
}