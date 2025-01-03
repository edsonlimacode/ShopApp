package com.edsonlimadev.shopapp.presenter.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edsonlimadev.shopapp.domain.usecases.category.GetAllCategoriesUseCase
import com.edsonlimadev.shopapp.utils.StateUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) : ViewModel() {

    private val _categoryList = MutableStateFlow<StateUi<List<String>>>(StateUi.Loading())
    val categoryList = _categoryList.asStateFlow()

    fun getAllCategories() = viewModelScope.launch {

        getAllCategoriesUseCase().fold(
            onSuccess = { categories ->
                _categoryList.value = StateUi.Success(categories)

            },
            onFailure = {
                _categoryList.value = StateUi.Error(it.message)
            })
    }
}