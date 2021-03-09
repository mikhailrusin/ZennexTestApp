package com.mikhailrusin.zennextestapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.mikhailrusin.zennextestapp.R
import com.mikhailrusin.zennextestapp.ui.common.factory.NewsFragmentFactory
import com.mikhailrusin.zennextestapp.ui.news_list.NewsListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.fragmentFactory = NewsFragmentFactory()
        supportFragmentManager.commit {
            replace(R.id.fragment_container, NewsListFragment::class.java, null)
        }
    }
}