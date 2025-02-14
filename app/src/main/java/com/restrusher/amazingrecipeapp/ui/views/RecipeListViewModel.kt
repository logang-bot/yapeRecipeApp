package com.restrusher.amazingrecipeapp.ui.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.restrusher.amazingrecipeapp.data.models.Recipe
import com.restrusher.amazingrecipeapp.data.models.RecipeResponse
import com.restrusher.amazingrecipeapp.network.RecipeApi
import com.restrusher.amazingrecipeapp.network.RecipeApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeListViewModel: ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _recipes = MutableStateFlow(listOf<Recipe>())
    val recipes = searchText
        .combine(_recipes) { text, recipes ->
            if (text.isBlank()) {
                recipes
            } else {
                recipes.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _recipes.value
        )

    init {
        getRecipes()
    }

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }

    private fun getRecipes() {
        viewModelScope.launch {
            val listResult = RecipeApi.retrofitService.getRecipes()
            _recipes.value = listResult.recipes
        }
    }
}