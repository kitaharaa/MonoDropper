package com.kitaharaa.monodropper.model.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MonoScreens(
    val path: String,
    val defaultIcon: ImageVector,
    val clickedIcon: ImageVector,
) {
    data object Base : MonoScreens("Base", Icons.Outlined.Home, Icons.Filled.Home)

    data object Stats : MonoScreens("Stats", Icons.Outlined.Star, Icons.Filled.Star)

    data object Settings : MonoScreens("Settings", Icons.Outlined.Settings, Icons.Filled.Settings)
}