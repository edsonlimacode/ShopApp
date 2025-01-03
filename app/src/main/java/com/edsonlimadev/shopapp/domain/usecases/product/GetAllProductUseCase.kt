package com.edsonlimadev.shopapp.domain.usecases.product

import com.edsonlimadev.shopapp.data.api.service.IProductServiceApi
import com.edsonlimadev.shopapp.domain.mapper.toDomain
import com.edsonlimadev.shopapp.domain.model.Product
import retrofit2.HttpException
import javax.inject.Inject

class GetAllProductUseCase @Inject constructor(
    private val productService: IProductServiceApi
) {

    suspend operator fun invoke(): Result<List<Product>> {
        return try {
            Result.success(productService.getProducts().map { it.toDomain() })
        } catch (ex: HttpException) {
            Result.failure(ex)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

}