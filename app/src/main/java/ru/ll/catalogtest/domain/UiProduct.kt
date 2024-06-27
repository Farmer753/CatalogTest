package ru.ll.catalogtest.domain

import com.squareup.moshi.Json

data class UiProduct(
    val title: String,
    val seoTitle: String?,
    val slug: String,
    val depth: Int,
    val icon: String,
    val webpIcon: String,
    val description: String?,
    val seoDescription: String?,
    val bannerImage: String?,
    val bannerMobileImage: String?,
    val bannerHref: String?,
    val subCategories: List<String>
)
