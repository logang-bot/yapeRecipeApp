package com.restrusher.amazingrecipeapp.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.restrusher.amazingrecipeapp.R
import com.restrusher.amazingrecipeapp.data.models.Recipe
import com.restrusher.amazingrecipeapp.data.models.RecipeDummyData
import com.restrusher.amazingrecipeapp.ui.theme.AmazingRecipeAppTheme

@Composable
fun RecipeItem(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipe.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.aspectRatio(1.8f).fillMaxWidth())
        Box(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomStart)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        colorResource(id = R.color.black)
                    )
                )
            )
            .padding(top = 20.dp, start = 6.dp, end = 6.dp, bottom = 5.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = recipe.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = colorResource(id = R.color.white),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun RecipeItemPreview() {
    AmazingRecipeAppTheme {
        RecipeItem(RecipeDummyData.DummyRecipe)
    }
}