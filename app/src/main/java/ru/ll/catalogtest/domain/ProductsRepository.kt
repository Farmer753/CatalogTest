package ru.ll.catalogtest.domain

interface ProductsRepository {
    suspend fun getData(): List<UiCategory>
}