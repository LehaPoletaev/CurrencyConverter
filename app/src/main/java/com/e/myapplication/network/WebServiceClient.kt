package com.e.myapplication.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object WebServiceClient {
    private var retrofit: Retrofit?=null

    val client : Retrofit
        get(){
            if(retrofit==null){
                retrofit= Retrofit.Builder()
                    .baseUrl("https://www.cbr-xml-daily.ru/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return  retrofit!!
        }
}