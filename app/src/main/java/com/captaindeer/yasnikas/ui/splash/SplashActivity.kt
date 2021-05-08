package com.captaindeer.yasnikas.ui.splash

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import com.captaindeer.yasnikas.R
import com.captaindeer.yasnikas.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //Hide top info (Date, Signal, Notifications...)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        //Change Activity after 2500 MilliS
        @Suppress("DEPRECATION")
        Handler().postDelayed(
            {
                //Launch the Login Activity
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish() // Call this when your activity is done and should be closed
            },
            2500
        )
    }
}