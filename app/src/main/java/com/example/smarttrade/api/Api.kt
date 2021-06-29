package com.example.smarttrade.api

import com.example.smarttrade.models.*
import com.example.smarttrade.utils.PreUtils
import com.orhanobut.hawk.Hawk
import retrofit2.Call
import retrofit2.http.*
import java.util.*
import kotlin.collections.ArrayList

interface Api {

    @FormUrlEncoded
    @POST("login")
    fun userLogin(
        @Field("login") login : String,
        @Field("password") password:String
    ) : Call<BaseModel<ArrayList<User>>>


    @GET("tovars")
    fun getTovars(@Query("sklad_id") sklad_id : Int):Call<BaseModel<ArrayList<Mahsulot>>>


    @FormUrlEncoded
    @POST("logout")
    fun deleteToken(
        @Field("tokenId") tokenId : String
    ): Call<BaseModel<ArrayList<String>>>


    @POST("tovars")
    fun postData(@Body postModel: PostModel) : Call<BaseModelSinov>

}