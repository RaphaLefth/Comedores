package com.example.comedor.Api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val service by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.37/API-PHP/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(Service::class.java)
    }
}