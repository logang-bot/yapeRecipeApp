package com.restrusher.amazingrecipeapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.restrusher.amazingrecipeapp.R

@Composable
fun RecipeDetailsScreen(
    recipeId: Int,
    onLocationButtonSelected: (Double, Double) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: RecipeDetailsViewModel = viewModel(factory = RecipeDetailsViewModel.Factory)
    viewModel.recipeId = recipeId
    val recipe by viewModel.recipe.collectAsState()

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .verticalScroll(rememberScrollState())) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipe?.image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1.1f)
                .fillMaxWidth())
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 5.dp),
            text = recipe?.name ?: "",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 5.dp),
            text = stringResource(R.string.instructions),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 5.dp),
            text = recipe?.instructions?.joinToString("\n") ?: "",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Start
        )

        Button(onClick = {
            onLocationButtonSelected(0.0, 0.0)
        }) {
            Text(text = stringResource(R.string.go_to_map))
        }
    }
}