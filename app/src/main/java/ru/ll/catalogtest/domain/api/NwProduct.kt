package ru.ll.catalogtest.domain.api

import com.squareup.moshi.Json

data class NwProduct(
    val title: String,
    @Json(name = "seo_title")
    val seoTitle: String?,
    val slug: String,
    val depth: Int,
    val icon: String,
    @Json(name = "webp_icon")
    val webpIcon: String,
    val description: String?,
    @Json(name = "seo_description")
    val seoDescription: String?,
    @Json(name = "banner_image")
    val bannerImage: String?,
    @Json(name = "banner_mobile_image")
    val bannerMobileImage: String?,
    @Json(name = "banner_href")
    val bannerHref: String?,
    //TODO List
    val subCategories: List<String>
)
