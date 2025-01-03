package com.edsonlimadev.shopapp.di

import com.edsonlimadev.shopapp.data.api.service.ICategoryServiceApi
import com.edsonlimadev.shopapp.data.api.service.IProductServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideOkhttp(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoryService(
        retrofit: Retrofit
    ): ICategoryServiceApi {
        return retrofit.create(ICategoryServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductService(
        retrofit: Retrofit
    ): IProductServiceApi {
        return retrofit.create(IProductServiceApi::class.java)
    }
}