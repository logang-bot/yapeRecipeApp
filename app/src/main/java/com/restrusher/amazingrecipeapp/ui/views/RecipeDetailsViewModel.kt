package com.restrusher.amazingrecipeapp.ui.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.restrusher.amazingrecipeapp.AmazingRecipeAppApplication
import com.restrusher.amazingrecipeapp.data.models.Recipe
import com.restrusher.amazingrecipeapp.data.repositores.RecipeRepository
import com.restrusher.amazingrecipeapp.network.RecipeApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(
    private val recipeRepository: RecipeRepository
): ViewModel() {

    private var _recipeId: Int = -1
    var recipeId get() = _recipeId
        set(value) {
            _recipeId = value
            getRecipe()
        }

    private val _recipe = MutableStateFlow<Recipe?>(null)
    val recipe = _recipe.asStateFlow()

    private fun getRecipe() {
        if (recipeId == -1)
            return

        viewModelScope.launch {
            try {
                val recipeResult = recipeRepository.getSingleRecipe(recipeId)
                _recipe.value = recipeResult
            } catch (e: Exception) {
                _recipe.value = null
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmazingRecipeAppApplication)
                val recipeRepository = application.container.recipeRepository
                RecipeDetailsViewModel(recipeRepository = recipeRepository)
            }
        }
    }
}