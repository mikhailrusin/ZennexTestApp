package com.mikhailrusin.zennextestapp.ui.news_overview

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.mikhailrusin.zennextestapp.R
import kotlinx.android.synthetic.main.fragment_news_overview.*
import kotlinx.android.synthetic.main.news_overview_toolbar.*

class NewsOverviewFragment : Fragment(R.layout.fragment_news_overview) {
    private var newsUrl: String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("url")?.let { url ->
            news_url.text = url
            newsUrl = url
            web_view.apply {
                loadUrl(url)
                webViewClient = WebViewClient()
                webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView?, newProgress: Int) {
                        if (progress < 100 && news_progressbar.visibility == View.GONE) {
                            news_progressbar.visibility = View.VISIBLE
                            pageLoading()
                        }
                        news_progressbar.progress = progress
                        if (progress == 100) {
                            news_progressbar.visibility = View.GONE
                            pageLoadFinished()
                        }
                    }
                }
            }
        }
        button_back.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        button_reload.setOnClickListener {
            web_view.loadUrl(newsUrl)
            pageLoading()
        }
        button_cancel.setOnClickListener {
            web_view.stopLoading()
            pageLoadFinished()
        }
    }

    private fun pageLoadFinished() {
        button_reload.visibility = View.VISIBLE
        button_cancel.visibility = View.GONE
    }

    private fun pageLoading() {
        button_reload.visibility = View.GONE
        button_cancel.visibility = View.VISIBLE
    }
}