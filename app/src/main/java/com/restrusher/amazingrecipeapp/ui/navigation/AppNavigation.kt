package com.restrusher.amazingrecipeapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.restrusher.amazingrecipeapp.ui.common.AppBar
import com.restrusher.amazingrecipeapp.ui.views.RecipeDetailsScreen
import com.restrusher.amazingrecipeapp.ui.views.RecipeListScreen
import com.restrusher.amazingrecipeapp.ui.views.RecipeLocationScreen

@Composable
fun HomeNavigation(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination
    var appBarTitle by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            AppBar(
                title = appBarTitle,
                currentDestination = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = RecipeListScreenRoute,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable<RecipeListScreenRoute> {
                RecipeListScreen(
                    onRecipeSelected = { id ->
                        navController.navigate(RecipeDetailsScreenRoute(recipeId = id))
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable<RecipeDetailsScreenRoute> {
                val args = it.toRoute<RecipeDetailsScreenRoute>()
                RecipeDetailsScreen(
                    onLocationButtonSelected = { lat, long ->
                        navController.navigate(RecipeLocationScreenRoute(lat, long))
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable<RecipeLocationScreenRoute>(
            ) {
                val args = it.toRoute<RecipeLocationScreenRoute>()
                RecipeLocationScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
