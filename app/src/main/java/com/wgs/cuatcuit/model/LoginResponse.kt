package com.wgs.cuatcuit.model

import com.google.gson.annotations.SerializedName

class LoginResponse {

    @SerializedName("email")
    var email: String? = null

    @SerializedName("password")
    var password: String? = null

    @SerializedName("tokken")
    var  token: String? = null
}