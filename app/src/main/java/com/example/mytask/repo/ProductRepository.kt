package com.example.mytask.repo

import com.example.mytask.api.RetrofitBuilder.productService
import com.example.mytask.model.FavoriteItem
import com.example.mytask.model.ProductListResponseClass
/*
* Class Description: Data class for favorite products
* */
open class ProductRepository {
    suspend fun getProducts(): ProductListResponseClass {
        return productService.getProductList()
    }

}