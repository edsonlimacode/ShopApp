package com.edsonlimadev.shopapp.presenter.product.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edsonlimadev.shopapp.domain.local.FavoriteLocal
import com.edsonlimadev.shopapp.domain.model.Product
import com.edsonlimadev.shopapp.domain.usecases.favorite.InsertFavoriteLocalUseCase
import com.edsonlimadev.shopapp.domain.usecases.product.GetProductByIdUseCase
import com.edsonlimadev.shopapp.utils.StateUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val insertFavoriteLocalUseCase: InsertFavoriteLocalUseCase
) : ViewModel() {

    private val _product = MutableStateFlow<StateUi<Product>>(StateUi.Loading())
    val products = _product.asStateFlow()

    private val _favorites = MutableStateFlow<StateUi<Unit>>(StateUi.Loading())
    val favorite = _favorites.asStateFlow()

    fun getProductById(productId: Int?) = viewModelScope.launch {

        getProductByIdUseCase(productId).fold(
            onSuccess = { product ->
                _product.value = StateUi.Loading()
                _product.value = StateUi.Success(product)
            },
            onFailure = {
                _product.value = StateUi.Error(it.message)
            }
        )
    }

    fun insertToFavorite(favoriteLocal: FavoriteLocal) = viewModelScope.launch {
        insertFavoriteLocalUseCase(favoriteLocal).fold(
            onSuccess = {
                _favorites.value = StateUi.Success(Unit)
            },
            onFailure = {
                _favorites.value = StateUi.Error(it.message)
            })
    }

}