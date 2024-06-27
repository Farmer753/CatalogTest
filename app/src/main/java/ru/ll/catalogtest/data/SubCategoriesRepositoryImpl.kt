package ru.ll.catalogtest.data

import ru.ll.catalogtest.domain.SubCategoriesRepository
import ru.ll.catalogtest.domain.UiSubCategorie
import ru.ll.catalogtest.domain.api.SubCategoriesApi

class SubCategoriesRepositoryImpl(
    private val subCategoriesApi: SubCategoriesApi,
    private val converter: Converter
) : SubCategoriesRepository {
    override suspend fun getData(): List<UiSubCategorie> {
        return subCategoriesApi.getAllSubCategories().map { converter.convert(it) }
    }

}