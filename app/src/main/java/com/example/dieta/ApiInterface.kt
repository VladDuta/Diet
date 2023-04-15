package com.example.dieta

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {


     @GET("foods.json")
    fun getData(): Call<List<MyDataItem>>

}