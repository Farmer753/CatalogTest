package ru.ll.catalogtest.data

import ru.ll.catalogtest.domain.ProductsRepository
import ru.ll.catalogtest.domain.UiProduct
import ru.ll.catalogtest.domain.api.ProductsApi

class ProductsRepositoryImpl(
    private val productsApi: ProductsApi,
    private val converter: Converter
) : ProductsRepository {
    override suspend fun getData(): List<UiProduct> {
        return productsApi.getAllProducts().map { converter.convert(it) }
    }
}