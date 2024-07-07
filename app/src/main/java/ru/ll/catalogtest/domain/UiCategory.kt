package ru.ll.catalogtest.domain

import kotlin.random.Random

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
        fun test(level:Int): UiCategory {
            return UiCategory(
                title = "Заголовок",
                slug = "Слаг",
                icon = ESKARO,
                subCategories = listOf()
//                subCategories = if (level!=0&&Random.nextBoolean()) {
//                    listOf()
//                } else {
////                    (0..Random.nextInt(5)).map { test(level-1) }
//                    listOf(test(0), test(0))
//                }
            )
        }
    }
}

