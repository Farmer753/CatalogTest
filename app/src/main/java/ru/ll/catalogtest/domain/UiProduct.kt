package ru.ll.catalogtest.domain

import com.squareup.moshi.Json
import ru.ll.catalogtest.domain.api.NwProductImage
import ru.ll.catalogtest.domain.api.NwPurchase

data class UiProduct(
    val id: Int,
    val title: String,
    val slug: String,
    val sky: Int,
    val description: String,
    val price: Int,
    val priceOld: Int,
    val sizeDiscount: Int,
    val images: List<String>
)
