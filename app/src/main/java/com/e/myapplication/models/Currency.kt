package com.e.myapplication.models

import com.google.gson.annotations.SerializedName

data class Currency (
    @SerializedName("CharCode")
    val charcode: String,
    @SerializedName("ID")
    val id: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Nominal")
    val nominal: Int,
    @SerializedName("NumCode")
    val numcode: String,
    @SerializedName("Previous")
    val previous: Double,
    @SerializedName("Value")
    val value: Double
)