package com.wgs.cuatcuit.model

import com.google.gson.annotations.SerializedName

class RegisterResponse {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("username")
    var username: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("password")
    var password: String? = null
}