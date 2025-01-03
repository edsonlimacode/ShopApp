package com.edsonlimadev.shopapp.presenter.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edsonlimadev.shopapp.domain.local.FavoriteLocal
import com.edsonlimadev.shopapp.domain.usecases.favorite.DeleteFavoriteLocalUseCase
import com.edsonlimadev.shopapp.domain.usecases.favorite.GetAllByUserIdLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAllByUserId: GetAllByUserIdLocalUseCase,
    private val deleteFavoriteLocalUseCase: DeleteFavoriteLocalUseCase
) : ViewModel() {

    private val _favorites = MutableStateFlow(FavoriteUiState())
    val favorite = _favorites.asStateFlow()

    fun getAll(userId: Int) = viewModelScope.launch {
        getAllByUserId(userId).fold(
            onSuccess = {
                it.collectLatest { favorites ->
                    _favorites.update { currentState ->
                        currentState.copy(
                            favoriteLocals = favorites
                        )
                    }
                }
            },
            onFailure = {
                _favorites.update { currentState ->
                    currentState.copy(
                        error = it.message
                    )
                }
            })
    }

    fun delete(favoriteLocal: FavoriteLocal) = viewModelScope.launch {
        deleteFavoriteLocalUseCase(favoriteLocal).fold(
            onSuccess = {
                _favorites.update { currentState ->
                    currentState.copy(
                        unit = Unit
                    )
                }
            },
            onFailure = {
                _favorites.update { currentState ->
                    currentState.copy(
                        error = it.message
                    )
                }
            })

    }
}

