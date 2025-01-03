package com.edsonlimadev.shopapp.data.api.service

import retrofit2.http.GET

interface ICategoryServiceApi {

    @GET("/products/categories")
    suspend fun getCategories(): List<String>

}