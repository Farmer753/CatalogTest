package ru.ll.catalogtest.domain

interface ProductsRepository {
    suspend fun getData(slug: String): List<UiProduct>
}