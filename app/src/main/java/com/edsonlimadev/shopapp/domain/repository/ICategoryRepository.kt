package com.edsonlimadev.shopapp.domain.repository

interface ICategoryRepository {

    suspend fun getCategories(): Result<List<String>>

}