package com.example.reqresin.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiConfig {
    const val baseUrl = "https://reqres.in/api/"

    fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() : ApiService{
        return getRetrofit().create(ApiService::class.java)
    }
}