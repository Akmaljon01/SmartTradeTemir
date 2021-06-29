package com.example.smarttrade.korzinka

import android.os.Bundle
import android.telecom.Call
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smarttrade.R
import com.example.smarttrade.api.NetworkManager
import com.example.smarttrade.models.*
import com.example.smarttrade.utils.PreUtils
import com.example.smarttrade.view.KorzinkaAdapter
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_korzina.*
import retrofit2.Response
import javax.security.auth.callback.Callback

class KorzinaActivity : AppCompatActivity() {

    var adapter1 : KorzinkaAdapter ? =null
    var items = ArrayList<SotTovar>()

    var summa = java.lang.Double.valueOf(0.0)
    var summadollar=java.lang.Double.valueOf(0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_korzina)

        recyccard.layoutManager=LinearLayoutManager(this)
        adapter1= KorzinkaAdapter(items)
        recyccard.adapter=adapter1
        items.addAll(Hawk.get(PreUtils.PREF_CARD))
        recyccard.adapter!!.notifyDataSetChanged()

        button=findViewById(R.id.summaforfirma)
        buttond=findViewById(R.id.summaforfirmadollar)
        for (d in items) {
            if (d.valyuta.equals("Сўм"))
            summa += d.summa
            else if (d.valyuta.equals("$"))
                summadollar+=d.summa
        }
        button.text=summa.toString()
        buttond.text=summadollar.toString()+"$"


        zakazyubor.setOnClickListener {


              var sottovar : ArrayList<SotTovar> = Hawk.get(PreUtils.PREF_CARD)
              var adminyubormodel = ArrayList<AdminYuborModel>()
            for (d in sottovar){
                var adminYuborModel = AdminYuborModel(d.tovar_id,d.miqdor,d.narx,d.summa)
                adminyubormodel.add(adminYuborModel)
            }

            var postModel=PostModel("Ali",adminyubormodel,button.text.toString().toDouble(),buttond.text.toString().toDouble(),25)


            NetworkManager.getApiServise1().postData(postModel).enqueue(object : retrofit2.Callback<BaseModelSinov>{
                override fun onFailure(call: retrofit2.Call<BaseModelSinov>, t: Throwable) {
                    Toast.makeText(this@KorzinaActivity,t.localizedMessage,Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: retrofit2.Call<BaseModelSinov>, response: Response<BaseModelSinov>) {
                    Toast.makeText(this@KorzinaActivity,response.body()?.message,Toast.LENGTH_LONG).show()
                }
            })
    }

    }
    companion object{
        lateinit var button: Button
        lateinit var buttond : Button
        fun changesum(summa : Double,summadollar : Double){
            button.text=summa.toString()
            buttond.text=summadollar.toString()+"$"
        }
    }
}