package com.mikhailrusin.zennextestapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.mikhailrusin.zennextestapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        bottom_nav.setOnNavigationItemSelectedListener { item ->
            toolbar.title = item.title
            when (item.itemId) {
                R.id.newsListFragment -> {
                    navController.navigate(R.id.newsListFragment)
                    true
                }
                R.id.imageFragment -> {
                    navController.navigate(R.id.imageFragment)
                    true
                }
                R.id.mapFragment -> {
                    navController.navigate(R.id.mapFragment)
                    true
                }
                else -> false
            }
        }
    }
}