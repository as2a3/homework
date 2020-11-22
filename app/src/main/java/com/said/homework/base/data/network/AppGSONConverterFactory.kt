package com.said.homework.base.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory

object AppGSONConverterFactory {
    private const val APP_SERVER_GMT_TIME_FORMAT = "yyyy-MM-dd HH:mm"
    val gson: Gson
        get() = GsonBuilder().setDateFormat(APP_SERVER_GMT_TIME_FORMAT).create()
    @JvmStatic
    val factory: GsonConverterFactory
        get() = GsonConverterFactory.create(gson)
}