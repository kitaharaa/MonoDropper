@file:OptIn(ExperimentalMaterial3Api::class)

package com.kitaharaa.monodropper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kitaharaa.monodropper.model.navigation.MonoScreens
import com.kitaharaa.monodropper.ui.navigation.MyNavHost
import com.kitaharaa.monodropper.ui.theme.MonoDropperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonoDropperTheme {
                val navController = rememberNavController()

                Scaffold(bottomBar = { BottomBar(navController = navController) }) { padding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    )
                    MyNavHost(navController = navController)
                }
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val iconList = listOf(
        MonoScreens.Base, MonoScreens.Stats, MonoScreens.Settings
    )
    NavigationBar(
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        iconList.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (currentRoute ==
                            item.path
                        ) item.clickedIcon else item.defaultIcon, contentDescription = item.path
                    )
                },
                label = {
                    AnimatedVisibility(
                        visible = currentRoute ==
                                item.path,
                        enter = fadeIn() + slideInVertically(
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing
                            )
                        ) { fullHeight ->
                            fullHeight
                        },
                        exit = fadeOut(spring()),
                    ) {
                        Text(
                            text = item.path,
                            fontSize = 14.sp
                        )
                    }
                },
                alwaysShowLabel = false,
                selected = currentRoute == item.path,
                onClick = {
                    navController.navigate(item.path) {

                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}