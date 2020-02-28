package com.wgs.cuatcuit.api

import com.ashokvarma.gander.GanderInterceptor
import com.wgs.cuatcuit.App
import com.wgs.cuatcuit.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Alvin Rusli on 01/24/2020.
 */
object ApiUtils {

    private lateinit var retrofit: Retrofit

    fun getInterface(baseUrl: String? = null): ApiInterface {
        return getRetrofit(baseUrl).create(ApiInterface::class.java)
    }

    private fun getRetrofit(baseUrl: String? = null): Retrofit {
        if (!ApiUtils::retrofit.isInitialized) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl ?: "http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
        }
        return retrofit
    }

    private fun getOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(logging)
        }
        okHttpClientBuilder.addInterceptor(GanderInterceptor(App.context)
                .showNotification(true))

        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
        return okHttpClientBuilder.build()
    }
}
