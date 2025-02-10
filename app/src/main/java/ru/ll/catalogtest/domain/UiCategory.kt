package ru.ll.catalogtest.domain

import kotlinx.serialization.Serializable
import ru.ll.catalogtest.utils.FuckingStringSerializer

@Serializable
data class UiCategory(
    val title: String,
    val slug: String,
    @Serializable(with = FuckingStringSerializer::class)
    val icon: String,
    val subCategories: List<UiCategory>
) {
    companion object {
        const val ESKARO = "https://vimos.ru/u/category/ObEcDB_1685695466.png"
        fun default(hasSubCategories: Boolean): UiCategory {
            return UiCategory(
                title = "Заголовок",
                slug = "Слаг",
                icon = "/u/category/ObEcDB_1685695466.png",
                subCategories = if (hasSubCategories) {
                    listOf(default(false), default(false))
                } else {
                    listOf()
                }
            )
        }
    }
}

