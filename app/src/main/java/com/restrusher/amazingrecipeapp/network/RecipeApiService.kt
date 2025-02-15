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

interface RecipeApiService {
    @GET("recipes/")
    suspend fun getRecipes() : RecipeResponse

    @GET("recipes/{id}")
    suspend fun getSingleRecipe(
        @Path("id") id: Int
    ): Recipe
}

object RecipeApi {

}
