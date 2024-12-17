package ru.ll.catalogtest.domain

import android.util.Base64
import kotlinx.serialization.Serializable

@Serializable
data class UiCategory(
    val title: String,
//    val seoTitle: String?,
    val slug: String,
//    val depth: Int,
    val icon: String,
//    val webpIcon: String,
//    val description: String?,
//    val seoDescription: String?,
//    val bannerImage: String?,
//    val bannerMobileImage: String?,
//    val bannerHref: String?,
    val subCategories: List<UiCategory>
) {
    companion object {
        const val ESKARO = "https://vimos.ru/u/category/ObEcDB_1685695466.png"
        fun default(hasSubCategories: Boolean): UiCategory {
            return UiCategory(
                title = "Заголовок",
                slug = "Слаг",
//                icon = ESKARO,
                icon = Base64.encodeToString("u/category/ObEcDB_1685695466.png".toByteArray(), Base64.DEFAULT),
                subCategories = if (hasSubCategories) {
                    listOf(default(false), default(false))
                } else {
                    listOf()
                }
            )
        }
    }
}

