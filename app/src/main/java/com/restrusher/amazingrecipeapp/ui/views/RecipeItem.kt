package com.restrusher.amazingrecipeapp.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.restrusher.amazingrecipeapp.data.models.Recipe
import com.restrusher.amazingrecipeapp.data.models.RecipeDummyData
import com.restrusher.amazingrecipeapp.ui.theme.AmazingRecipeAppTheme

@Composable
fun RecipeItem(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {

}

@Preview
@Composable
fun RecipeItemPreview() {
    AmazingRecipeAppTheme {
        RecipeItem(RecipeDummyData.DummyRecipe)
    }
}