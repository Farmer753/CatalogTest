package ru.ll.catalogtest.domain.api

import retrofit2.http.GET

interface CategoriesApi {

    @GET("categories/-/catalog")
    suspend fun getAllCategories(): List<NwCategory>
}