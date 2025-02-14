package com.restrusher.amazingrecipeapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.restrusher.amazingrecipeapp.data.models.RecipeDummyData
import com.restrusher.amazingrecipeapp.ui.theme.AmazingRecipeAppTheme

@Composable
fun RecipeListScreen(
    onRecipeSelected: (String) -> Unit, modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.primaryContainer)) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(RecipeDummyData.DummyRecipeList) { recipe ->
                RecipeItem(recipe = recipe)
            }
        }
    }
}

@Preview
@Composable
fun RecipeListScreenPreview() {
    AmazingRecipeAppTheme {
        RecipeListScreen(onRecipeSelected = {})
    }
}