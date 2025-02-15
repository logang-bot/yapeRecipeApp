package com.restrusher.amazingrecipeapp.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlin.random.Random

@Composable
fun RecipeLocationScreen(
    modifier: Modifier = Modifier
) {
    val singapore = generateRandomLatLong(-90.0, 90.0, -180.0, 180.0)
    val singaporeMarkerState = rememberMarkerState(position = singapore)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = singaporeMarkerState,
            title = "Singapore",
            snippet = "Marker in Singapore"
        )
    }
}

// TODO: Replace this with real data, I'm using this function just to simulate location data since the api I'm using doesn't have such data
fun generateRandomLatLong(minLat: Double, maxLat: Double, minLong: Double, maxLong: Double): LatLng {
    val latitude = Random.nextDouble(minLat, maxLat)
    val longitude = Random.nextDouble(minLong, maxLong)
    return LatLng(latitude, longitude)
}