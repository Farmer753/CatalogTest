package ru.ll.catalogtest.domain

interface SubCategoriesRepository {
    suspend fun getData(): List<UiSubCategorie>
}