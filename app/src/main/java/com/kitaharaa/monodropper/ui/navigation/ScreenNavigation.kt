package com.kitaharaa.monodropper.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kitaharaa.monodropper.model.navigation.MonoScreens
import com.kitaharaa.monodropper.ui.base.BaseScreen
import com.kitaharaa.monodropper.ui.base.cardList
import com.kitaharaa.monodropper.ui.settings.SettingScreen
import com.kitaharaa.monodropper.ui.stats.StatsScreen
import com.kitaharaa.monodropper.view_model.BaseViewModel

@Composable
fun MyNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MonoScreens.Base.path) {
        composable(MonoScreens.Base.path) {
            val baseViewModel: BaseViewModel = hiltViewModel()
            BaseScreen(cardList)
        }
        composable(MonoScreens.Stats.path) {
            StatsScreen(hiltViewModel())
        }
        composable(MonoScreens.Settings.path) {
            SettingScreen(hiltViewModel())
        }
    }
}