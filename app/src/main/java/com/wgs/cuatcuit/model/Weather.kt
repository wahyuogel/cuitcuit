package com.wgs.cuatcuit.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Alvin Rusli on 01/24/2020.
 */
class Weather {

    @SerializedName("id")
    var id: Long? = null

    @SerializedName("main")
    var main: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("icon")
    var icon: String? = null
}
