package com.wgs.cuatcuit.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Alvin Rusli on 01/24/2020.
 */
class CuitListResponse {

    @SerializedName("data")
    var data: List<Cuit>? = null
}
