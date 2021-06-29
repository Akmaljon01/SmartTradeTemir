package com.example.smarttrade.asosiyOyna

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.smarttrade.R
import com.example.smarttrade.api.NetworkManager
import com.example.smarttrade.dialog.OpenDialog
import com.example.smarttrade.korzinka.KorzinaActivity
import com.example.smarttrade.loginOyna.MainActivity
import com.example.smarttrade.models.BaseModel
import com.example.smarttrade.models.Mahsulot
import com.example.smarttrade.models.User
import com.example.smarttrade.utils.PreUtils
import com.example.smarttrade.view.MahsulotAdapter
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_aosiy.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.Locale.filter
import kotlin.collections.ArrayList

class AosiyActivity : AppCompatActivity() {
    var adapter1 :MahsulotAdapter?=null
    var items = ArrayList<Mahsulot>()
    var fullitems = ArrayList<Mahsulot>()

    private var backPressedTime: Long = 0

    var asosiyViewModel = AsosiyViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aosiy)
        asosiyViewModel=ViewModelProvider(this).get(AsosiyViewModel::class.java)
        asosiyViewModel.getMahsulotlar(25)


        mahsulotlar.layoutManager=GridLayoutManager(this,2)
        adapter1= MahsulotAdapter(items)
        mahsulotlar.adapter=adapter1

        asosiyViewModel.mahsulotlar.observe(this, Observer {
           // Toast.makeText(this, it.get(0).nomi, Toast.LENGTH_LONG).show()
            items.addAll(it)
            fullitems.addAll(it)
           //mahsulotlar.adapter=MahsulotAdapter(it)
           mahsulotlar.adapter!!.notifyDataSetChanged()
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menumain, menu)
        val item=menu?.findItem(R.id.menuSearch)
        val searchView=item?.actionView as SearchView
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var newitems=ArrayList<Mahsulot>()
                var text=newText.toString().toLowerCase()
                newitems.clear()
                if (text.isEmpty()){
                    newitems=fullitems
                }else{
                    for (d in fullitems){
                        if (d.nomi.toLowerCase().contains(text)){
                            newitems.add(d)
                        }
                    }
                }
                items.clear()
                items.addAll(newitems)
                mahsulotlar.adapter!!.notifyDataSetChanged()

                return false
            }

        })



        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if (id==R.id.menuitemlogout){
            NetworkManager.getApiServise().deleteToken(PreUtils.getTokenId()).enqueue(object : Callback<BaseModel<ArrayList<String>>>{
                override fun onFailure(call: Call<BaseModel<ArrayList<String>>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<BaseModel<ArrayList<String>>>,
                    response: Response<BaseModel<ArrayList<String>>>
                ) {
                   if (response.body()!!.status){
                       Hawk.delete(PreUtils.PREF_TOKEN)
                       Hawk.delete(PreUtils.PREF_USER)
                       val intent = Intent(this@AosiyActivity, MainActivity::class.java)
                       startActivity(intent)
                       finish()
                   }
                    else{
               Toast.makeText(this@AosiyActivity, response.body()?.message, Toast.LENGTH_LONG).show()
                   }
                }

            })
        }

        if(id==R.id.menuitemcard){
            val intent = Intent(this@AosiyActivity, KorzinaActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finishAffinity()
            System.exit(0)
        } else {
            Toast.makeText(
                    this,
                    "dasturdan chiqish uchun yana bir marta bosing",
                    Toast.LENGTH_SHORT
            ).show()
        }
        backPressedTime = System.currentTimeMillis()
    }


}