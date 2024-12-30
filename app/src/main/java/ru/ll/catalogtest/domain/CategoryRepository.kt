package ru.ll.catalogtest.domain

interface CategoryRepository {
    suspend fun getData(): List<UiCategory>
}