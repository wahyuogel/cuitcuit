package com.wgs.cuatcuit.api

import com.wgs.cuatcuit.model.CuitListResponse
import com.wgs.cuatcuit.model.CurrentWeatherResponse
import com.wgs.cuatcuit.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

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
//    @GET("content")
    fun getCuitList(
        @Query("search") searchQuery: String?,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<CuitListResponse>

    @POST("/login")
    fun postLogin(
//        @Body()
    ): Call<LoginResponse>

    @POST("/register")
    fun postRegister(
//        @Body()
    ): Call<LoginResponse>
}
