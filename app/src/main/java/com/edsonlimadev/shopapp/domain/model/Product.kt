package com.edsonlimadev.shopapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: String,
    val title: String,
    val rating: Rating
): Parcelable