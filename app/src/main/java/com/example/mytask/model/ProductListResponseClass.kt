package com.example.mytask.model
/*
* Class Description: Data class for products api call
* */
data class ProductListResponseClass(
    val products: List<Product>
) : java.io.Serializable {
    data class Product(
        val addToCartButtonText: String,
        val badges: List<String>,
        val brand: String,
        val citrusId: String,
        val id: String,
        val imageURL: String,
        val isAddToCartEnable: Boolean,
        val isDeliveryOnly: Boolean,
        val isDirectFromSupplier: Boolean,
        val isFindMeEnable: Boolean,
        val isInTrolley: Boolean,
        val isInWishlist: Boolean,
        val messages: Messages,
        val price: List<Price>,
        val purchaseTypes: List<PurchaseType>,
        val ratingCount: Double,
        val saleUnitPrice: Double,
        val title: String,
        val totalReviewCount: Int,
        var isfavorite: Boolean = false
    ) : java.io.Serializable {
        data class Price(
            val isOfferPrice: Boolean,
            val message: String,
            val value: Double
        ) : java.io.Serializable

        data class PurchaseType(
            val cartQty: Int,
            val displayName: String,
            val maxQtyLimit: Int,
            val minQtyLimit: Int,
            val purchaseType: String,
            val unitPrice: Double
        ) : java.io.Serializable

        data class Messages(
            val promotionalMessage: String,
            val sash: Sash,
            val secondaryMessage: String
        ) : java.io.Serializable

        class Sash

    }
}