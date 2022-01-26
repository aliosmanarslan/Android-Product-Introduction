package com.aliosmanarslan.aliexpress


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import com.aliosmanarslan.aliexpress.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {
    lateinit var bind:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(bind.root)

        Handler().postDelayed({

            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2500)




    }
}