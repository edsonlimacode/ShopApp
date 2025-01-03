package com.edsonlimadev.shopapp.data.api.repository

import com.edsonlimadev.shopapp.data.api.service.ICategoryServiceApi
import com.edsonlimadev.shopapp.domain.repository.ICategoryRepository
import retrofit2.HttpException
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryService: ICategoryServiceApi
) : ICategoryRepository {
    override suspend fun getCategories(): Result<List<String>> {

        return try {
            Result.success(categoryService.getCategories())
        } catch (ex: HttpException) {
            Result.failure(ex)
        }
        catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}