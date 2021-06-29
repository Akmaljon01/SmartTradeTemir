package com.example.smarttrade.models

import androidx.lifecycle.MutableLiveData

data class BaseModel<T>(
    val status:Boolean,
    val data : T,
    val message:String
)