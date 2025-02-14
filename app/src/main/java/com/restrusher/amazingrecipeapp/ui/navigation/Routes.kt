package com.restrusher.amazingrecipeapp.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
data object RecipeListScreenRoute

@Serializable
data class RecipeDetailsScreenRoute(
    val recipeId: String,
)

@Serializable
data class RecipeLocationScreenRoute(
    val lat: String,
    val long: String
)