package ru.ll.catalogtest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.ll.catalogtest.data.Converter
import ru.ll.catalogtest.data.ProductsRepositoryImpl
import ru.ll.catalogtest.data.SubCategoriesRepositoryImpl
import ru.ll.catalogtest.domain.ProductsRepository
import ru.ll.catalogtest.domain.SubCategoriesRepository
import ru.ll.catalogtest.domain.api.ProductsApi
import ru.ll.catalogtest.domain.api.SubCategoriesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideConverter(
    ): Converter {
        return Converter()
    }

    @Provides
    @Singleton
    fun provideProductsRepository(
        productsApi: ProductsApi,
        converter: Converter
    ): ProductsRepository {
        return ProductsRepositoryImpl(productsApi, converter)
    }

    @Provides
    @Singleton
    fun provideSubCategoriesRepository(
        subCategoriesApi: SubCategoriesApi,
        converter: Converter
    ): SubCategoriesRepository {
        return SubCategoriesRepositoryImpl(subCategoriesApi, converter)
    }
}