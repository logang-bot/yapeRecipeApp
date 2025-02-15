package com.restrusher.amazingrecipeapp.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
data object RecipeListScreenRoute

@Serializable
data class RecipeDetailsScreenRoute(
    val recipeId: Int,
    val recipeName: String
)

@Serializable
data class RecipeLocationScreenRoute(
    val lat: Float,
    val long: Float
)