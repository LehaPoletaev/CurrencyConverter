package com.e.myapplication.models

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.HashMap

data class ValuteCurs(

    @SerializedName("Date")
    val date: String,
    @SerializedName("PreviousDate")
    val previousDate: String,
    @SerializedName("PreviousURL")
    val previousURL: String,
    @SerializedName("Timestamp")
    val timestamp: String,
    @SerializedName("Valute")
    val valute: HashMap<String, Currency>
)