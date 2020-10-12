package com.e.myapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.e.myapplication.models.ValuteCurs
import com.e.myapplication.network.BackEndApi
import com.e.myapplication.network.WebServiceClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import org.json.JSONObject
import java.net.URL


class HomeViewModel(application: Application):AndroidViewModel(application){
    val resp :BackEndApi = WebServiceClient.client.create(BackEndApi::class.java)
    //Converted value
    val convertValute = MutableLiveData<String>()

    private val _convertedValute = MutableLiveData<String>()
    val convertedValute: LiveData<String>
        get() = _convertedValute

    // Selected item from spinners
    val spinnerSelectedPositionFirst = MutableLiveData<Int>()
    val spinnerSelectedPositionSecond = MutableLiveData<Int>()
    //For fill spinners
    val  _charcodes = MutableLiveData<ArrayList<String>>()
    val charcodes : LiveData<ArrayList<String>>
        get() = _charcodes
    //For fill spinners
    var nameCurrency : ArrayList<String> = ArrayList()
    var nameCharCode: ArrayList<String> = ArrayList()

    private fun fillSpinners(){

        GlobalScope.launch(Dispatchers.IO) {
            val response = resp.GetDailyCource().awaitResponse()
            if (response.isSuccessful) {
                val data: ValuteCurs = response.body()!!

                for(key in data.valute.keys){
                   nameCurrency.add(data.valute.get(key)?.name.toString())
                }
                _charcodes.postValue(nameCurrency)
            }
        }
    }
    fun  getValute(){
        GlobalScope.launch(Dispatchers.IO) {
            try{
                val response = resp.GetDailyCource().awaitResponse()

                if (response.isSuccessful) {
                val data: ValuteCurs = response.body()!!

                for(key in data.valute.keys){
                     nameCharCode.add(data.valute.get(key)?.charcode.toString())
                }

                    val baseCurrency= spinnerSelectedPositionFirst.value?.let { nameCharCode.get(it) }
                    val convertedToCurrency= spinnerSelectedPositionSecond.value?.let { nameCharCode.get(it) }

                println(baseCurrency)
                println(convertedToCurrency)
                var API = "https://api.ratesapi.io/api/latest?base=$baseCurrency&symbols=$convertedToCurrency"

                val apiResult = URL(API).readText()
                val jsonObject = JSONObject(apiResult)
                val response =jsonObject.getJSONObject("rates").getString(convertedToCurrency).toFloat()
                _convertedValute.postValue((convertValute.value.toString().toFloat()*response).toString())
                }
                }
            catch (e: Exception){
               Log.e("Main","$e")
            }
        }
    }
    init{
        fillSpinners()
    }
}