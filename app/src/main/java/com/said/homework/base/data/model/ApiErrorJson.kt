package com.said.homework.base.data.model

import com.google.gson.annotations.SerializedName

class ApiErrorJson<T> {
    @SerializedName("code")
    var code = 0

    @SerializedName("message")
    var message: String? = null

    @SerializedName("data")
    var data: T? = null
        private set

    fun setData(data: T) {
        this.data = data
    }
}