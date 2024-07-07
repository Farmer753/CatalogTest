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
import ru.ll.catalogtest.domain.api.CategoriesApi
import ru.ll.catalogtest.domain.api.ProductApi
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
        categoriesApi: CategoriesApi,
        converter: Converter
    ): ProductsRepository {
        return ProductsRepositoryImpl(categoriesApi, converter)
    }

    @Provides
    @Singleton
    fun provideSubCategoriesRepository(
        productApi: ProductApi,
        converter: Converter
    ): SubCategoriesRepository {
        return SubCategoriesRepositoryImpl(productApi, converter)
    }
}