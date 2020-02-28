package com.wgs.cuatcuit.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Alvin Rusli on 01/24/2020.
 */
class Cuit {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var date: String? = null

    @SerializedName("image")
    var image: String? = null

    @SerializedName("image_thumb")
    var message: String? = null

    @SerializedName("is_liked")
    var isLiked: Boolean = false
}
