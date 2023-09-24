package com.kitaharaa.monodropper.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kitaharaa.monodropper.view_model.SettingViewModel

@Composable
fun SettingScreen(viewModel: SettingViewModel) {
    Box {
        Text(modifier = Modifier.fillMaxSize(), text = "SettingsScreen")
    }
}