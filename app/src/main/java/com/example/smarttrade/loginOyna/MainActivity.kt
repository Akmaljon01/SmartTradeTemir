package com.example.smarttrade.loginOyna

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.smarttrade.R
import com.example.smarttrade.asosiyOyna.AosiyActivity
import com.example.smarttrade.dialog.OpenDialog
import com.example.smarttrade.models.Mahsulot
import com.example.smarttrade.utils.PreUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mainViewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        mainViewModel=ViewModelProvider(this).get(MainViewModel::class.java)
        btn_kirish.setOnClickListener {
            mainViewModel.getUser(edt_login.text.toString().trim(),edt_parol.text.toString().trim())
            mainViewModel.ochiq.observe(this, Observer {
                if (it){
                  mainViewModel.user.observe(this, Observer {
                      PreUtils.setToken(it.get(0).token.toString(),it)
                      //PreUtils.setToken1(it.get(0).token.toString())
                      val intent =Intent(this,AosiyActivity::class.java)
                      startActivity(intent)

                  })
                }else{
                    Toast.makeText(this,"xatolik",Toast.LENGTH_LONG).show()
                }
            })
            mainViewModel.message.observe(this, Observer {
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            })

            mainViewModel.error.observe(this, Observer {
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            })
        }

    }




}