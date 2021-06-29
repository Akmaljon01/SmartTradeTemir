package com.example.smarttrade.asosiyOyna

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smarttrade.api.NetworkManager
import com.example.smarttrade.models.BaseModel
import com.example.smarttrade.models.Mahsulot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AsosiyViewModel : ViewModel() {

     var mahsulotlar = MutableLiveData<ArrayList<Mahsulot>>()
    var xabar = MutableLiveData<String>()
    var error = MutableLiveData<String>()

    fun getMahsulotlar(id:Int){
        //error.value="test"
        NetworkManager.getApiServise1().getTovars(id).enqueue(object : Callback<BaseModel<ArrayList<Mahsulot>>>{
            override fun onFailure(call: Call<BaseModel<ArrayList<Mahsulot>>>, t: Throwable) {
                error.value=t.localizedMessage
            }

            override fun onResponse(
                call: Call<BaseModel<ArrayList<Mahsulot>>>,
                response: Response<BaseModel<ArrayList<Mahsulot>>>
            ) {
                if (response.body()?.status!!){
                    mahsulotlar.value=response.body()?.data
                }

                xabar.value=response.body()?.message
            }
        })

    }

}