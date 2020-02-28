package com.wgs.cuatcuit.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Alvin Rusli on 01/24/2020.
 */
class Temperature {

    @SerializedName("temp")
    var temp: Double? = null

    @SerializedName("feels_like")
    var feelsLike: Double? = null

    @SerializedName("temp_min")
    var tempMin: Double? = null

    @SerializedName("temp_max")
    var tempMax: Double? = null

    @SerializedName("pressure")
    var pressure: Double? = null

    @SerializedName("humidity")
    var humidity: Double? = null
}
