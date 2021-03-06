package com.mikhailrusin.zennextestapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.mikhailrusin.zennextestapp.R
import com.mikhailrusin.zennextestapp.ui.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splash_image.clipToOutline = true
        Handler().postDelayed( {
            startActivity(Intent(this, MainActivity::class.java))
        }, 500)
    }
}