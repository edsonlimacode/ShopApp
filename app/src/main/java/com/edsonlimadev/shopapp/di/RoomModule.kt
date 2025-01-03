package com.edsonlimadev.shopapp.di

import android.content.Context
import androidx.room.Room
import com.edsonlimadev.shopapp.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "db-shopaap"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(
        database: AppDatabase
    ) = database.favoriteDao()

    @Provides
    @Singleton
    fun provideCartDao(
        database: AppDatabase
    ) = database.cartDao()

}