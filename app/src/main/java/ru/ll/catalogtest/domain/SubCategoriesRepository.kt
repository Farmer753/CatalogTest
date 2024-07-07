package ru.ll.catalogtest.domain

interface SubCategoriesRepository {
    suspend fun getData(slug: String): List<UiProduct>
}