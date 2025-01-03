package com.edsonlimadev.shopapp.presenter.favorite

import com.edsonlimadev.shopapp.domain.local.FavoriteLocal

data class FavoriteUiState(
    val favoriteLocals: List<FavoriteLocal> = emptyList(),
    val unit: Unit? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)