package com.edsonlimadev.shopapp.data.api.service

import com.edsonlimadev.shopapp.data.api.response.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface IProductServiceApi {

    @GET("/products")
    suspend fun getProducts(): List<ProductResponse>

    @GET("/products/category/{name}")
    suspend fun getProductsByCategoryName(
        @Path("name") name: String?
    ): List<ProductResponse>

    @GET("/products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int?
    ): ProductResponse

}