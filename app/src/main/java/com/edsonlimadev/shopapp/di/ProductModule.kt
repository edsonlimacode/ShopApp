package com.edsonlimadev.shopapp.di

import com.edsonlimadev.shopapp.data.api.repository.ProductRepository
import com.edsonlimadev.shopapp.domain.repository.IProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ProductModule {

    @Binds
    abstract fun bindProductRepository(
        productRepository: ProductRepository
    ): IProductRepository

}