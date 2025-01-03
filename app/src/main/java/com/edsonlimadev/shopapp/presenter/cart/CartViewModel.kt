package com.edsonlimadev.shopapp.presenter.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edsonlimadev.shopapp.domain.local.ProductLocal
import com.edsonlimadev.shopapp.domain.usecases.cart.AddProductToCartLocalUseCase
import com.edsonlimadev.shopapp.domain.usecases.cart.AddQuantityProductLocalUseCase
import com.edsonlimadev.shopapp.domain.usecases.cart.ClearCartLocalUseCase
import com.edsonlimadev.shopapp.domain.usecases.cart.CountCartItemsLocalUseCase
import com.edsonlimadev.shopapp.domain.usecases.cart.DeleteProductLocalByIdUseCase
import com.edsonlimadev.shopapp.domain.usecases.cart.GetAllCartItemsLocalUseCase
import com.edsonlimadev.shopapp.domain.usecases.cart.GetProductLocalByIdUseCase
import com.edsonlimadev.shopapp.domain.usecases.cart.RemoveQuantityProductLocalUseCase
import com.edsonlimadev.shopapp.domain.usecases.cart.UpdateCartLocalUseCase
import com.edsonlimadev.shopapp.utils.StateUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val addProductToCartLocalUseCase: AddProductToCartLocalUseCase,
    private val getProductLocalByIdUseCase: GetProductLocalByIdUseCase,
    private val updateCartLocalUseCase: UpdateCartLocalUseCase,
    private val getAllCartLocalUseCase: GetAllCartItemsLocalUseCase,
    private val deleteProductLocalByIdUseCase: DeleteProductLocalByIdUseCase,
    private val addQuantityProductLocalUseCase: AddQuantityProductLocalUseCase,
    private val removeQuantityProductLocalUseCase: RemoveQuantityProductLocalUseCase,
    private val clearCartLocalUseCase: ClearCartLocalUseCase,
    private val countCartItemsLocalUseCase: CountCartItemsLocalUseCase
) : ViewModel() {

    private val _product = MutableLiveData<StateUi<ProductLocal?>>()
    val product: LiveData<StateUi<ProductLocal?>> = _product

    private val _unit = MutableLiveData<StateUi<Unit>>()
    val unit: LiveData<StateUi<Unit>> = _unit

    private val _products = MutableLiveData<StateUi<List<ProductLocal?>>>()
    val products: LiveData<StateUi<List<ProductLocal?>>> = _products

    private val _count = MutableLiveData<StateUi<Int>>()
    val count: LiveData<StateUi<Int>> = _count

    fun addToCart(productLocal: ProductLocal) = viewModelScope.launch {
        addProductToCartLocalUseCase(productLocal).fold(
            onSuccess = {
                _unit.value = StateUi.Success(it)
            },
            onFailure = {
                _unit.value = StateUi.Error(it.message.toString())
            }
        )
    }


    fun getProductById(userId: Int, productId: Int) = viewModelScope.launch {

        _product.value = StateUi.Loading()

        getProductLocalByIdUseCase(userId, productId).fold(
            onSuccess = {
                _product.value = StateUi.Success(it)
            },
            onFailure = {
                _product.value = StateUi.Error(it.message.toString())
            }
        )
    }

    fun deleteProductById(userId: Int, productId: Int) = viewModelScope.launch {

        _unit.value = StateUi.Loading()

        deleteProductLocalByIdUseCase(userId, productId).fold(
            onSuccess = {
                _unit.value = StateUi.Success(it)
            },
            onFailure = {
                _unit.value = StateUi.Error(it.message.toString())
            }
        )
    }

    fun addQuantityProduct(userId: Int, productId: Int) = viewModelScope.launch {

        _unit.value = StateUi.Loading()

        addQuantityProductLocalUseCase(userId, productId).fold(
            onSuccess = {
                _unit.value = StateUi.Success(it)
            },
            onFailure = {
                _unit.value = StateUi.Error(it.message.toString())
            }
        )
    }

    fun removeQuantityProduct(userId: Int, productId: Int) = viewModelScope.launch {

        _unit.value = StateUi.Loading()

        removeQuantityProductLocalUseCase(userId, productId).fold(
            onSuccess = {
                _unit.value = StateUi.Success(it)
            },
            onFailure = {
                _unit.value = StateUi.Error(it.message.toString())
            }
        )
    }

    fun getAllItems(userId: Int) = viewModelScope.launch {

        _products.value = StateUi.Loading()

        getAllCartLocalUseCase(userId).fold(
            onSuccess = {
                it.collectLatest {
                    _products.value = StateUi.Success(it)
                }
            },
            onFailure = {
                _products.value = StateUi.Error(it.message.toString())
            }
        )
    }

    fun update(productLocal: ProductLocal) = viewModelScope.launch {

        _product.value = StateUi.Loading()

        updateCartLocalUseCase(productLocal).fold(
            onSuccess = {},
            onFailure = {
                _product.value = StateUi.Error(it.message.toString())
            }
        )
    }

    fun clearCart(userId: Int) = viewModelScope.launch {

        _product.value = StateUi.Loading()

        clearCartLocalUseCase(userId).fold(
            onSuccess = {},
            onFailure = {
                _product.value = StateUi.Error(it.message.toString())
            }
        )
    }

    fun countCart(userId: Int) = viewModelScope.launch {

        countCartItemsLocalUseCase(userId).fold(
            onSuccess = {
                _count.value = StateUi.Success(it)
            },
            onFailure = {
                _product.value = StateUi.Error(it.message.toString())
            }
        )
    }


}