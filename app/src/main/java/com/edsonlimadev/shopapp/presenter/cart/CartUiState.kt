package com.edsonlimadev.shopapp.presenter.cart

import com.edsonlimadev.shopapp.domain.local.ProductLocal

data class CartUiState(
    val isLoading: Boolean = false,
    val products: List<ProductLocal> = emptyList(),
    val product: ProductLocal? = null,
    val unit: Unit? = null,
    val error: String? = null
)