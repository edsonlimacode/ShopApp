package com.edsonlimadev.shopapp.domain.usecases.product

import com.edsonlimadev.shopapp.data.api.service.IProductServiceApi
import com.edsonlimadev.shopapp.domain.mapper.toDomain
import com.edsonlimadev.shopapp.domain.model.Product
import retrofit2.HttpException
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val productService: IProductServiceApi
) {

    suspend operator fun invoke(productId: Int?): Result<Product> {
        return try {
            Result.success(productService.getProductById(productId).toDomain())
        } catch (ex: HttpException) {
            Result.failure(ex)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

}