package com.wgs.cuatcuit.api

import com.wgs.cuatcuit.model.CuitListResponse
import com.wgs.cuatcuit.model.CurrentWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Alvin Rusli on 01/24/2020.
 */
interface ApiInterface {

    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("appid") apiKey: String?,
        @Query("units") units: String?,
        @Query("q") query: String?
    ): Call<CurrentWeatherResponse>

    @GET("products")
    fun getCuitList(
        @Query("query") searchQuery: String?,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<CuitListResponse>
}
