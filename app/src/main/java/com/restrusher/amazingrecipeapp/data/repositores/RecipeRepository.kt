package com.restrusher.amazingrecipeapp.data.repositores

import com.restrusher.amazingrecipeapp.data.models.Recipe
import com.restrusher.amazingrecipeapp.network.RecipeApiService

interface RecipeRepository {
    suspend fun getRecipes(): List<Recipe>
    suspend fun getSingleRecipe(recipeId: Int): Recipe
}

class NetworkRecipeRepositoryImpl(
    private val recipeApiService: RecipeApiService
): RecipeRepository {
    override suspend fun getRecipes(): List<Recipe> {
        return recipeApiService.getRecipes().recipes
    }

    override suspend fun getSingleRecipe(recipeId: Int): Recipe {
        return recipeApiService.getSingleRecipe(recipeId)
    }
}

