package ru.ll.catalogtest.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.ll.catalogtest.data.CategoryRepositoryImpl
import ru.ll.catalogtest.data.Converter
import ru.ll.catalogtest.data.ProductsRepositoryImpl
import ru.ll.catalogtest.domain.ApiConst.API_URL
import ru.ll.catalogtest.domain.CategoryRepository
import ru.ll.catalogtest.domain.ProductsRepository
import ru.ll.catalogtest.domain.api.CategoriesApi
import ru.ll.catalogtest.domain.api.ProductApi
import timber.log.Timber
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
    fun provideCategoryRepository(
        categoriesApi: CategoriesApi,
        converter: Converter
    ): CategoryRepository {
        return CategoryRepositoryImpl(categoriesApi, converter)
    }

    @Provides
    @Singleton
    fun provideProductsRepository(
        productApi: ProductApi,
        converter: Converter
    ): ProductsRepository {
        return ProductsRepositoryImpl(productApi, converter)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor {
                    Timber.tag("OkHttp").d(it)
                }.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(API_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoriesApi(
        retrofit: Retrofit
    ): CategoriesApi {
        return retrofit.create(CategoriesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductsApi(
        retrofit: Retrofit
    ): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }
}