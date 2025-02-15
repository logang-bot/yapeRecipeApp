package com.restrusher.amazingrecipeapp.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.restrusher.amazingrecipeapp.data.repositores.NetworkRecipeRepositoryImpl
import com.restrusher.amazingrecipeapp.data.repositores.RecipeRepository
import com.restrusher.amazingrecipeapp.network.RecipeApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val recipeRepository: RecipeRepository
}

class DefaultAppContainer: AppContainer {

    private val baseUrl = "https://dummyjson.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: RecipeApiService by lazy {
        retrofit.create(RecipeApiService::class.java)
    }

    override val recipeRepository: RecipeRepository by lazy {
        NetworkRecipeRepositoryImpl(retrofitService)
    }
}