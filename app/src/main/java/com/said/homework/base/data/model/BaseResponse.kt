package com.said.homework.base.data.model

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("totalResults")
    var totalResults = 0L
}