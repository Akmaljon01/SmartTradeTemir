package com.example.smarttrade.api

import com.example.smarttrade.utils.Constants
import com.example.smarttrade.utils.PreUtils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {

    var retrofit : Retrofit?=null
    var api : Api ? =null
    fun getApiServise() : Api{
        retrofit=Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.Base_URL).build()
        api=retrofit?.create(Api::class.java)

        return api!!
    }


    fun getApiServise1(): Api{
         val client= OkHttpClient.Builder()
             .addInterceptor(OAuthInterceptor("Bearer",PreUtils.getToken()))
             .build()
        retrofit=Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.Base_URL)
            .client(client)
            .build()
        api=retrofit?.create(Api::class.java)

        return api!!
    }


}