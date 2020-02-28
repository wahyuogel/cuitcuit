package com.wgs.cuatcuit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wgs.cuatcuit.api.ApiUtils
import com.wgs.cuatcuit.model.core.Resource
import com.wgs.cuatcuit.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.charset.Charset

class LoginViewModel : ViewModel() {

    val resource = MutableLiveData<Resource<Any>>()
    val liveData = MutableLiveData<Resource<LoginResponse>>()

    fun postLogin(email: String?, pass: String?) {
        liveData.postValue(Resource.loading())

        val call = ApiUtils.getInterface("35.187.237.58:8080").postLogin()

        call.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                liveData.postValue(Resource.error(t))
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    liveData.postValue(Resource.success(response.body()))
                } else {
                    val errorBytes = response.errorBody()!!.bytes()
                    val errorBody = String(errorBytes, Charset.forName("UTF-8"))
                    liveData.postValue(Resource.error(Exception(errorBody)))
                }
            }
        })
    }
}