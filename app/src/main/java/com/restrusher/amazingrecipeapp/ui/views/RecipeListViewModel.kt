package com.restrusher.amazingrecipeapp.ui.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.restrusher.amazingrecipeapp.AmazingRecipeAppApplication
import com.restrusher.amazingrecipeapp.data.models.Recipe
import com.restrusher.amazingrecipeapp.data.models.RecipeResponse
import com.restrusher.amazingrecipeapp.data.repositores.RecipeRepository
import com.restrusher.amazingrecipeapp.network.RecipeApi
import com.restrusher.amazingrecipeapp.network.RecipeApiService
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeListViewModel(
    private val recipeRepository: RecipeRepository
): ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _recipes = MutableStateFlow(listOf<Recipe>())
    @OptIn(FlowPreview::class)
    val recipes = searchText
        .debounce(500L)
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
            try {
                val listResult = recipeRepository.getRecipes()
                _recipes.value = listResult
            } catch (e: Exception) {
                _recipes.value = listOf()
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmazingRecipeAppApplication)
                val recipeRepository = application.container.recipeRepository
                RecipeListViewModel(recipeRepository = recipeRepository)
            }
        }
    }
}