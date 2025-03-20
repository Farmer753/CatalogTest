package ru.ll.catalogtest.domain.api

import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("products/{categorySlug}/catalog")
    suspend fun getAllProduct(@Path("categorySlug") categorySlug: String): List<NwProductSlug>

    @GET("products/{slug}")
    suspend fun getProduct(@Path("slug") slug: String): NwProduct
}