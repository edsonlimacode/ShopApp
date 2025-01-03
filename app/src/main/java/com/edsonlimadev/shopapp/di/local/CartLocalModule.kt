package com.edsonlimadev.shopapp.di.local

import com.edsonlimadev.shopapp.data.local.repository.CartRepository
import com.edsonlimadev.shopapp.data.local.repository.ICartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CartLocalModule {

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        cartRepository: CartRepository
    ): ICartRepository


}