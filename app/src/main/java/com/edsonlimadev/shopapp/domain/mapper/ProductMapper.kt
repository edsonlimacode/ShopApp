package com.edsonlimadev.shopapp.domain.mapper

import com.edsonlimadev.shopapp.data.api.response.ProductResponse
import com.edsonlimadev.shopapp.data.api.response.RatingResponse
import com.edsonlimadev.shopapp.data.local.entity.FavoriteEntity
import com.edsonlimadev.shopapp.data.local.entity.ProductEntity
import com.edsonlimadev.shopapp.domain.local.FavoriteLocal
import com.edsonlimadev.shopapp.domain.local.ProductLocal
import com.edsonlimadev.shopapp.domain.model.Product
import com.edsonlimadev.shopapp.domain.model.Rating

fun ProductResponse.toDomain(): Product {
    return Product(
        id = this.id,
        category = this.category,
        description = this.description,
        image = this.image,
        price = this.price,
        title = this.title,
        rating = this.rating.toDomain()
    )
}

fun RatingResponse.toDomain(): Rating {
    return Rating(
        rate = this.rate,
        count = this.count
    )
}

fun FavoriteEntity.toDomain(): FavoriteLocal {
    return FavoriteLocal(
        id = this.id,
        userId = this.userId,
        productId = this.productId,
        title = this.title,
        price = this.price,
        image = this.image
    )
}


fun FavoriteLocal.toEntity(): FavoriteEntity {
    return FavoriteEntity(
        id = this.id,
        userId = this.userId,
        productId = this.productId,
        title = this.title,
        price = this.price,
        image = this.image
    )
}

fun ProductEntity.toDomain(): ProductLocal {
    return ProductLocal(
        id = this.id,
        userId = this.userId,
        productId = this.productId,
        title = this.title,
        price = this.price,
        image = this.image,
        quantity = this.quantity
    )
}

fun ProductLocal.toEntity(): ProductEntity {
    return ProductEntity(
        id = this.id,
        userId = this.userId,
        productId = this.productId,
        title = this.title,
        price = this.price,
        image = this.image,
        quantity = this.quantity
    )
}
