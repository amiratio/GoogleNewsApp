package com.maa.googlenewsapp.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetroClass {

    val BASE_URL= "https://newsapi.org/"
    fun retroInstance(): Retrofit{
        val okhttp= OkHttpClient().newBuilder().connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).
            writeTimeout(60, TimeUnit.SECONDS).build()
        return Retrofit.Builder().baseUrl(BASE_URL).client(okhttp).addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun apiService(): ApiService{
        return retroInstance().create(ApiService::class.java)
    }

}