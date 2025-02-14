package com.restrusher.amazingrecipeapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.restrusher.amazingrecipeapp.data.models.Recipe
import com.restrusher.amazingrecipeapp.data.models.RecipeResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://dummyjson.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface RecipeApiService {
    @GET("recipes/")
    suspend fun getRecipes() : RecipeResponse

    @GET("/{id}")
    fun getSingleRecipe(
        @Path("id") id: Int
    ): Recipe
}

object RecipeApi {
    val retrofitService: RecipeApiService by lazy {
        retrofit.create(RecipeApiService::class.java)
    }
}
