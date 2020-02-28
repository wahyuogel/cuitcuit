package com.wgs.cuatcuit.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Alvin Rusli on 01/24/2020.
 */
class CurrentWeatherResponse {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("main")
    var temperature: Temperature? = null

    @SerializedName("weather")
    var weathers: List<Weather>? = null
}
