package com.example.smarttrade.models

import java.lang.StringBuilder

data class SotTovar(
        val tovarnomi:String,
        val tovar_id:Int,
        val miqdor:Double,
        val narx:Double,
        val summa:Double,
        val rasm:String,
        val valyuta :String
)