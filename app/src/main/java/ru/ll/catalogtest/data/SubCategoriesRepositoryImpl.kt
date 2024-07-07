package ru.ll.catalogtest.data

import ru.ll.catalogtest.domain.SubCategoriesRepository
import ru.ll.catalogtest.domain.UiProduct
import ru.ll.catalogtest.domain.api.NwProductSlug
import ru.ll.catalogtest.domain.api.ProductApi

class SubCategoriesRepositoryImpl(
    private val productApi: ProductApi,
    private val converter: Converter
) : SubCategoriesRepository {
    override suspend fun getData(slug: String): List<UiProduct> {
        return productApi.getAllProduct(slug).map {
            val nwProduct = productApi.getProduct(it.slug)
            converter.convert(nwProduct)
        }
    }

}