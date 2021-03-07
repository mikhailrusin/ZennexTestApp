package com.mikhailrusin.zennextestapp.ui.factory

import androidx.fragment.app.FragmentFactory
import com.mikhailrusin.zennextestapp.ui.news_list.NewsListFragment
import com.mikhailrusin.zennextestapp.ui.news_overview.NewsOverviewFragment

class NewsFragmentFactory : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String) =
            when (className) {
                NewsListFragment::class.java.name -> NewsListFragment()
                NewsOverviewFragment::class.java.name -> NewsOverviewFragment()
                else -> super.instantiate(classLoader, className)
            }
}