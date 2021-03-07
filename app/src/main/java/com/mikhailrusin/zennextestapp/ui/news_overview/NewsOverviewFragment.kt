package com.mikhailrusin.zennextestapp.ui.news_overview

import android.os.Bundle
import android.view.View
import android.webkit.*
import androidx.fragment.app.Fragment
import com.mikhailrusin.zennextestapp.R
import kotlinx.android.synthetic.main.fragment_news_overview.*

class NewsOverviewFragment : Fragment(R.layout.fragment_news_overview) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            bundle.getString("url")?.let { url ->
                web_view.apply {
                    loadUrl(url)
                    webViewClient = WebViewClient()
                    webChromeClient = WebChromeClient()
                }
            }
        }
    }
}