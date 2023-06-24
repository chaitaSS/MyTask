package com.example.mytask.model
/*
* Class Description: Data class for favorite products
* */
data class FavoriteItem(
    val product: ProductListResponseClass.Product?,
    val isFavorite: Boolean
)
