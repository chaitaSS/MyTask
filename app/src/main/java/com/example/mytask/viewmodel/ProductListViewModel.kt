package com.example.mytask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytask.model.ProductListResponseClass
import com.example.mytask.repo.ProductRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
/*
* Viewmodel class for all products
* */
class ProductListViewModel : ViewModel() {
    @set:Inject
    var productRepository: ProductRepository = ProductRepository()
    val _products = MutableLiveData<ProductListResponseClass>()
    val products: LiveData<ProductListResponseClass> = _products

    init {
        viewModelScope.launch {
            _products.value = productRepository.getProducts()
        }
    }
    
}