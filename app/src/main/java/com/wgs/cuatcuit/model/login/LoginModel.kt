package com.wgs.cuatcuit.model.login

import com.google.gson.annotations.SerializedName

class LoginModel {

    @SerializedName("email")
    var email: String? = null

    @SerializedName("password")
    var password: String? = null

}