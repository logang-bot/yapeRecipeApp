package com.restrusher.amazingrecipeapp.data.models

data class Recipe(
    val id: String,
    val name: String,
    val description: String,
    val latitude: String,
    val longitude: String,
    val imageUrl: String
) {
}

object RecipeDummyData {
    val DummyRecipe: Recipe = Recipe(
        id = "111",
        name = "Pizza",
        description = "Cheese with dough",
        latitude = "111",
        longitude = "22",
        imageUrl = "https://picsum.photos/200/300"
    )
    val DummyRecipeList: List<Recipe> = listOf(DummyRecipe)
}