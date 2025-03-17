package ru.ll.catalogtest.data

import ru.ll.catalogtest.domain.ProductsRepository
import ru.ll.catalogtest.domain.UiProduct
import ru.ll.catalogtest.domain.api.ProductApi

class ProductsRepositoryImpl(
    private val productApi: ProductApi,
    private val converter: Converter
) : ProductsRepository {

    override suspend fun getData(slug: String): List<UiProduct> {
        return productApi.getAllProduct(slug).map {
            val nwProduct = productApi.getProduct(it.slug)
            converter.convert(nwProduct)
        }
    }

    override suspend fun getProduct(slug: String): UiProduct {
        return productApi.getProduct(slug).let {
            converter.convert(it)
        }
    }
}