package com.restrusher.amazingrecipeapp.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val caloriesPerServing: Int = 0,
    val cookTimeMinutes: Int = 0,
    val cuisine: String = "",
    val difficulty: String = "",
    val id: Int = 0,
    val image: String = "",
    val ingredients: List<String> = listOf(),
    val instructions: List<String> = listOf(),
    val mealType: List<String> = listOf(),
    val name: String = "",
    val prepTimeMinutes: Int = 0,
    val rating: Double = 0.0,
    val reviewCount: Int = 0,
    val servings: Int = 0,
    val tags: List<String> = listOf(),
    val userId: Int = 0
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name,
            ingredients.joinToString("")
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

object RecipeDummyData {
    val DummyRecipe: Recipe = Recipe(
        id = 0,
        name = "Testing recipe",
        instructions = listOf("Cheese with dough"),
        image = "https://picsum.photos/200/300"
    )
    val DummyRecipeList: List<Recipe> = listOf(DummyRecipe, DummyRecipe, DummyRecipe)
}