package com.example.smarttrade.models

data class PostModel(
        val mijoz:String,
        val data : ArrayList<AdminYuborModel>,
        val summa_som:Double,
        val summa_dol:Double,
        val sklad_id:Int
)