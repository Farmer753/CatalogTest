package ru.ll.catalogtest.data

import ru.ll.catalogtest.domain.CategoryRepository
import ru.ll.catalogtest.domain.UiCategory
import ru.ll.catalogtest.domain.api.CategoriesApi

class CategoryRepositoryImpl(
    private val categoriesApi: CategoriesApi,
    private val converter: Converter
) : CategoryRepository {

    override suspend fun getData(): List<UiCategory> {
        return categoriesApi.getAllCategories().map { converter.convert(it) }
    }
}