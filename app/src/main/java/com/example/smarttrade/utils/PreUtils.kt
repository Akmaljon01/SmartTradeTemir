package com.example.smarttrade.utils

import com.example.smarttrade.models.SotTovar
import com.example.smarttrade.models.User
import com.orhanobut.hawk.Hawk
import java.util.*
import kotlin.collections.ArrayList

object PreUtils {

    const val PREF_TOKEN="token"
    const val PREF_USER="user"
    const val PREF_CARD="korzinka"

   fun addcard(tovar : SotTovar ){
       var card=Hawk.get(PREF_CARD,ArrayList<SotTovar>())

       if (card.isEmpty()){
           card.add(tovar)
       }
       else{
           var checkCard:Boolean=true
           for (t in card){
               if (t.tovar_id==tovar.tovar_id)
               {
                   checkCard=false
                   break
               }
           }
           if (checkCard){
               card.add(tovar)
           }

       }
       Hawk.delete(PREF_CARD)
       Hawk.put(PREF_CARD,card)
   }

    fun removeCardElement(tovar : SotTovar){
        var card=Hawk.get(PREF_CARD,ArrayList<SotTovar>())
        card.remove(tovar)
        Hawk.delete(PREF_CARD)
        Hawk.put(PREF_CARD,card)
    }

    fun setToken(token : String,users: ArrayList<User>){
        var token1=Hawk.get(PREF_TOKEN, String())
        var user=Hawk.get(PREF_USER,ArrayList<User>())


        if (token1.isEmpty()&&user.isEmpty()) {
            Hawk.put(PREF_TOKEN,token)
            Hawk.put(PREF_USER,users)
        }else{
            Hawk.delete(PREF_TOKEN)
            Hawk.delete(PREF_USER)
            Hawk.put(PREF_USER,users)
            Hawk.put(PREF_TOKEN,token)
        }

    }

    fun setToken1(token : String){
        var token1=Hawk.get(PREF_TOKEN, String())
        if (token1.isEmpty()) {
            Hawk.put(PREF_TOKEN,token)

        }else{
            Hawk.delete(PREF_TOKEN)
            Hawk.put(PREF_TOKEN,token)
        }

    }

    fun getToken(): String {
        return Hawk.get(PREF_TOKEN, String())
    }

    fun getTokenId():String{
        var user=Hawk.get(PreUtils.PREF_USER,ArrayList<User>())

        var id=user[0].tokenID

        return id
    }

    fun deleteUser(){
        Hawk.delete(PREF_TOKEN)
        Hawk.delete(PREF_USER)
    }

}