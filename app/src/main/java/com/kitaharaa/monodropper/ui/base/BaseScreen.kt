package com.kitaharaa.monodropper.ui.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kitaharaa.monodropper.view_model.BaseViewModel

@Composable
fun BaseScreen(viewModel: BaseViewModel) {
    Box {
        Text(modifier = Modifier.fillMaxSize(), text = "BaseScreen")
    }
}