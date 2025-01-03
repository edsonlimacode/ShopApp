package com.edsonlimadev.shopapp.presenter.product

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edsonlimadev.shopapp.domain.model.Product
import com.edsonlimadev.shopapp.domain.usecases.product.GetAllProductByCategoryNameUseCase
import com.edsonlimadev.shopapp.domain.usecases.product.GetAllProductUseCase
import com.edsonlimadev.shopapp.domain.usecases.product.GetProductByIdUseCase
import com.edsonlimadev.shopapp.utils.StateUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getAllProductUseCase: GetAllProductUseCase,
    private val getProductsByCategoryNameUseCase: GetAllProductByCategoryNameUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<StateUi<List<Product>>>(StateUi.Loading())
    val products = _products.asStateFlow()

    private val _productsByCategory = MutableStateFlow<StateUi<List<Product>>>(StateUi.Loading())
    val productsByCategory = _productsByCategory.asStateFlow()

    fun getProducts() = viewModelScope.launch {

        getAllProductUseCase().fold(
            onSuccess = { products ->
                _products.value = StateUi.Success(products)
            },
            onFailure = {
                _products.value = StateUi.Error(it.message)
            }
        )
    }

    fun getProductsByCategoryName(category: String?) = viewModelScope.launch {

        getProductsByCategoryNameUseCase(category).fold(
            onSuccess = { products ->
                _productsByCategory.value = StateUi.Loading()
                delay(3000)
                _productsByCategory.value = StateUi.Success(products)
            },
            onFailure = {
                _productsByCategory.value = StateUi.Error(it.message)
            }
        )
    }
}