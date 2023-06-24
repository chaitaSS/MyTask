package com.example.mytask.api


import com.example.mytask.model.ProductListResponseClass
import retrofit2.http.GET
/*
* API Interface to declare api calls
* */
interface ProductService {

    @GET("/v3/2f06b453-8375-43cf-861a-06e95a951328")
    suspend fun getProductList(): ProductListResponseClass


}