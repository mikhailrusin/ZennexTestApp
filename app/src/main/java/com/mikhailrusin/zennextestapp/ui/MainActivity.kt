package com.mikhailrusin.zennextestapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.mikhailrusin.zennextestapp.R
import com.mikhailrusin.zennextestapp.ui.news_list.NewsListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var hideMenu = false

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        bottom_nav.setOnNavigationItemSelectedListener { item ->
            toolbar.title = item.title
            when (item.itemId) {
                R.id.newsListFragment -> {
                    hideMenu = false
                    invalidateOptionsMenu()
                    navController.navigate(R.id.newsListFragment)
                    true
                }
                R.id.imageFragment -> {
                    hideMenu = true
                    invalidateOptionsMenu()
                    navController.navigate(R.id.imageFragment)
                    true
                }
                R.id.mapFragment -> {
                    hideMenu = true
                    invalidateOptionsMenu()
                    navController.navigate(R.id.mapFragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {
        if (!findNavController(R.id.nav_host_fragment).popBackStack()) {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return if (hideMenu) {
            false
        } else {
            menuInflater.inflate(R.menu.options_menu, menu)
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.refresh -> {
                (nav_host_fragment.childFragmentManager.fragments[0] as NewsListFragment)
                    .refreshNews()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}