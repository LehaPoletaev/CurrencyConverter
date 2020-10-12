package com.e.myapplication.network

import com.e.myapplication.models.ValuteCurs
import retrofit2.Call
import retrofit2.http.GET

interface BackEndApi {
    @GET("daily_json.js")
    fun GetDailyCource(): Call<ValuteCurs>
}