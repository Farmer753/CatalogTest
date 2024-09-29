package ru.ll.catalogtest.data

import kotlinx.coroutines.delay
import ru.ll.catalogtest.domain.ProductsRepository
import ru.ll.catalogtest.domain.UiCategory
import ru.ll.catalogtest.domain.api.CategoriesApi

class ProductsRepositoryImpl(
    private val categoriesApi: CategoriesApi,
    private val converter: Converter
) : ProductsRepository {
    override suspend fun getData(): List<UiCategory> {
        delay(1500)
        return categoriesApi.getAllCategories().map { converter.convert(it) }
    }
}