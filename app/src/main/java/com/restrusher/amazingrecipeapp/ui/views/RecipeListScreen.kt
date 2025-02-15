package com.restrusher.amazingrecipeapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.restrusher.amazingrecipeapp.R
import com.restrusher.amazingrecipeapp.ui.theme.AmazingRecipeAppTheme

@Composable
fun RecipeListScreen(
    onRecipeSelected: (Int, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: RecipeListViewModel = viewModel(factory = RecipeListViewModel.Factory)
    val searchText by viewModel.searchText.collectAsState()
    val recipes by viewModel.recipes.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {

        TextField(value = searchText,
            onValueChange = viewModel::onSearchTextChanged,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = stringResource(R.string.search_by_name_or_ingredient)) })
        Spacer(modifier = Modifier.height(16.dp))
        if (recipes.isNotEmpty()) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(recipes) { recipe ->
                    RecipeItem(recipe = recipe, modifier = Modifier.clickable {
                        onRecipeSelected(recipe.id, recipe.name)
                    })
                }
            }
        } else {
            Text(
                text = stringResource(R.string.something_went_wrong),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().wrapContentHeight())
        }
    }
}

@Preview
@Composable
fun RecipeListScreenPreview() {
    AmazingRecipeAppTheme {
        RecipeListScreen(onRecipeSelected = { a, b ->

        })
    }
}