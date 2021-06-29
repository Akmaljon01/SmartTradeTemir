package com.example.smarttrade.loginOyna

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.smarttrade.R
import com.example.smarttrade.asosiyOyna.AosiyActivity
import com.example.smarttrade.utils.PreUtils

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

      Handler().postDelayed({

          val token=PreUtils.getToken()
          if (token.isEmpty()){
          val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()}
          else{
              val intent = Intent(this, AosiyActivity::class.java)
              startActivity(intent)
              finish()
          }
        }, 3000)

    }
}