package com.example.mytask.viewmodel

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytask.model.FavoriteItem
import com.example.mytask.model.ProductListResponseClass
import com.example.mytask.repo.ProductRepository
import com.example.mytask.utils.SharedPrefUtil
import com.google.gson.Gson
import kotlinx.coroutines.launch
import javax.inject.Inject
/*
* Viewmodel class for favorite products
* */
class FavoritesViewModel : ViewModel() {
    @set:Inject
    var productRepository: ProductRepository = ProductRepository()


    private val _favoriteProducts = MutableLiveData<List<ProductListResponseClass.Product>>()
    val favoriteProducts: LiveData<List<ProductListResponseClass.Product>> get() = _favoriteProducts

    fun getFavoriteList(product: ProductListResponseClass) {
        _favoriteProducts.value = product.products.filter { it.isfavorite }

    }


    fun removeFavorite(product: ProductListResponseClass.Product?) {
        product?.isfavorite = false
        _favoriteProducts.value = _favoriteProducts.value?.filter { it.id != product?.id }
    }
}