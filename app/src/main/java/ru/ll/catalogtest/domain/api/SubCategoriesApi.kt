package ru.ll.catalogtest.domain.api

import retrofit2.http.GET

interface SubCategoriesApi {
    @GET("getAllSubCategories")
    suspend fun getAllSubCategories(): List<NwSubCategorie>
}