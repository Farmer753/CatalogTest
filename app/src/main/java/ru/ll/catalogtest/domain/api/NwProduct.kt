package ru.ll.catalogtest.domain.api

import com.squareup.moshi.Json

data class NwProduct(
    val id: Int,
    val title: String,
    val slug: String,
    val sku: Int,
    val description: String?,
    val purchase: NwPurchase,
    val images: List<NwProductImage>
)

data class NwPurchase(
    val price: Int,
    @Json(name = "price_old")
    val priceOld: Int,
    @Json(name = "size_discount")
    val sizeDiscount: Double,
)

data class NwProductImage(
    val original: String
)