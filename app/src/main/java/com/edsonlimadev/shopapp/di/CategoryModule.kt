package com.edsonlimadev.shopapp.di

import com.edsonlimadev.shopapp.data.api.repository.CategoryRepository
import com.edsonlimadev.shopapp.domain.repository.ICategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class CategoryModule {

    @Binds
    abstract fun bindCategoryRepository(
        categoryRepository: CategoryRepository
    ): ICategoryRepository

}