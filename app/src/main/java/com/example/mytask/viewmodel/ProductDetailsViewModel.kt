package com.example.mytask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytask.model.ProductListResponseClass
import com.example.mytask.repo.ProductRepository
import javax.inject.Inject
/*
* Viewmodel class for product details
* */
class ProductDetailsViewModel : ViewModel() {
    @set:Inject
    var productRepository: ProductRepository = ProductRepository()
    private val _product = MutableLiveData<ProductListResponseClass.Product>()
    val product: LiveData<ProductListResponseClass.Product>
        get() = _product

    fun setSelectedProduct(product: ProductListResponseClass.Product?) {
        _product.value = product!!

    }
}