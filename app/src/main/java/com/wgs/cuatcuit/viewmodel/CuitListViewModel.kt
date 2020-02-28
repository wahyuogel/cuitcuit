package com.wgs.cuatcuit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wgs.cuatcuit.api.ApiUtils
import com.wgs.cuatcuit.model.CurrentWeatherResponse
import com.wgs.cuatcuit.model.core.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.charset.Charset

/**
 * Created by Alvin Rusli on 01/24/2020.
 */
class CuitListViewModel : ViewModel() {

    val liveData = MutableLiveData<Resource<CurrentWeatherResponse>>()

    fun getCurrentWeather(city: String?) {
        liveData.postValue(Resource.loading())
        val call = ApiUtils.getInterface().getCurrentWeather(
            apiKey = "APIKEY",
            units = "metric",
            query = city)
        call.enqueue(object : Callback<CurrentWeatherResponse> {
            override fun onResponse(call: Call<CurrentWeatherResponse>, response: Response<CurrentWeatherResponse>) {
                if (response.isSuccessful) {
                    liveData.postValue(Resource.success(response.body()))
                } else {
                    val errorBytes = response.errorBody()!!.bytes()
                    val errorBody = String(errorBytes, Charset.forName("UTF-8"))
                    liveData.postValue(Resource.error(Exception(errorBody)))
                }
            }

            override fun onFailure(call: Call<CurrentWeatherResponse>, t: Throwable) {
                liveData.postValue(Resource.error(t))
            }
        })
    }
}
