package com.restrusher.amazingrecipeapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.restrusher.amazingrecipeapp.R
import com.restrusher.amazingrecipeapp.data.models.RecipeDummyData
import com.restrusher.amazingrecipeapp.ui.theme.AmazingRecipeAppTheme

@Composable
fun RecipeListScreen(
    onRecipeSelected: (String) -> Unit, modifier: Modifier = Modifier
) {
    val viewModel = viewModel<RecipeListViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val recipes by viewModel.recipes.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {

        TextField(value = searchText,
            onValueChange = viewModel::onSearchTextChanged,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = stringResource(R.string.search)) })
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(recipes) { recipe ->
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