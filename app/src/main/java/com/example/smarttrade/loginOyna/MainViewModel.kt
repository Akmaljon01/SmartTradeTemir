package com.example.smarttrade.loginOyna


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smarttrade.api.NetworkManager
import com.example.smarttrade.models.BaseModel
import com.example.smarttrade.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : ViewModel() {

    val error=MutableLiveData<String>()
    val message=MutableLiveData<String>()
    val user=MutableLiveData<ArrayList<User>>()
    val ochiq=MutableLiveData<Boolean>()

    fun getUser(login: String , parol : String){
        error.value="test"
        NetworkManager.getApiServise().userLogin(login,parol).enqueue(object : Callback<BaseModel<ArrayList<User>>>{
            override fun onFailure(call: Call<BaseModel<ArrayList<User>>>, t: Throwable) {
                error.value=t.localizedMessage
            }
            override fun onResponse(
                call: Call<BaseModel<ArrayList<User>>>,
                response: Response<BaseModel<ArrayList<User>>>
            ) {

                   if(response.body()!!.status){
                       ochiq.value=true
                   user.value=response.body()?.data

                   }

                message.value=response.body()?.message


            }
        })
}


}


