package com.edsonlimadev.shopapp.di.local

import com.edsonlimadev.shopapp.data.local.repository.FavoriteRepository
import com.edsonlimadev.shopapp.data.local.repository.IFavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class FavoriteLocalModule {

    @Binds
    @Singleton
    abstract fun bindFavoriteRepository(
        favoriteRepository: FavoriteRepository
    ): IFavoriteRepository
}