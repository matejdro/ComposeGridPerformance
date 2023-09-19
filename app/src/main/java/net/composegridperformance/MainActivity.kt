package net.composegridperformance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import net.composegridperformance.feature.colors.ColorsGridScreen
import net.composegridperformance.feature.colorspaginated.ColorsPaginatedGridScreen
import net.composegridperformance.feature.images.ImagesGridScreen
import net.composegridperformance.feature.menu.MenuScreen
import net.composegridperformance.navigation.NavigationRoute
import net.composegridperformance.ui.theme.ComposeGridPerformanceTheme


@OptIn(ExperimentalAnimationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeGridPerformanceTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics { testTagsAsResourceId = true },
                    color = Color.Black,
                ) { NavHost(navController = rememberNavController()) }
            }
        }
    }

    @ExperimentalAnimationApi
    @Composable
    fun NavHost(navController: NavHostController) {
        NavHost(navController, startDestination = NavigationRoute.Menu.route) {
            composable(route = NavigationRoute.Menu.route) {
                MenuScreen(navController = navController)
            }
            composable(NavigationRoute.ImagesGrid.route) {
                ImagesGridScreen()
            }
            composable(NavigationRoute.ColorsGrid.route) {
                ColorsGridScreen()
            }
            composable(NavigationRoute.PaginatedColorsGrid.route) {
                ColorsPaginatedGridScreen()
            }
        }
    }
}