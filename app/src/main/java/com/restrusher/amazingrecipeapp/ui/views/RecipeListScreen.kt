package com.restrusher.amazingrecipeapp.ui.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.restrusher.amazingrecipeapp.data.models.RecipeDummyData

@Composable
fun RecipeListScreen(
    onRecipeSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(RecipeDummyData.DummyRecipeList) { recipe ->
            RecipeItem(recipe = recipe)
        }
    }
}