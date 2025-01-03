package com.edsonlimadev.shopapp.domain.usecases.category

import com.edsonlimadev.shopapp.domain.mapper.toDomain
import com.edsonlimadev.shopapp.domain.repository.ICategoryRepository
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val categoryRepository: ICategoryRepository
) {
    suspend operator fun invoke(): Result<List<String>> {
        return categoryRepository.getCategories()
    }
}