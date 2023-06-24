package com.example.mytask.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
* RetrofitBuilder to create retrofit object
* */
object RetrofitBuilder {
    private fun getRetrofit(): Retrofit {
        val BASEURL = "https://run.mocky.io"
        val httpClient = OkHttpClient.Builder().build()
        return Retrofit.Builder().baseUrl(BASEURL).client(httpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()

    }

    val productService: ProductService = getRetrofit().create(ProductService::class.java)
}