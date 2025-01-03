package com.edsonlimadev.shopapp.domain.usecases.product

import com.edsonlimadev.shopapp.data.api.service.IProductServiceApi
import com.edsonlimadev.shopapp.domain.mapper.toDomain
import com.edsonlimadev.shopapp.domain.model.Product
import retrofit2.HttpException
import javax.inject.Inject

class GetAllProductByCategoryNameUseCase @Inject constructor(
    private val productService: IProductServiceApi
) {

    suspend operator fun invoke(category: String?): Result<List<Product>> {
        return try {
            Result.success(productService.getProductsByCategoryName(category).map { it.toDomain() })
        } catch (ex: HttpException) {
            Result.failure(ex)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

}