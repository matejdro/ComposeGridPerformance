package net.composegridperformance.feature.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import net.composegridperformance.navigation.NavigationRoute

@Composable
fun MenuScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .testTag("menu_images_list"),
            onClick = { navController.navigate(NavigationRoute.ImagesGrid.route) },
        ) {
            Text("Image List (Coil)")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .testTag("menu_paginated_images_list"),
            onClick = { navController.navigate(NavigationRoute.ImagesGrid.route) },
        ) {
            Text("Paginated Image List (Coil)")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .testTag("menu_glide_images_list"),
            onClick = { navController.navigate(NavigationRoute.GlideImagesGrid.route) },
        ) {
            Text("Image List (Glide)")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .testTag("menu_colors_list"),
            onClick = { navController.navigate(NavigationRoute.ColorsGrid.route) },
        ) {
            Text("Color List")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .testTag("menu_paginated_colors_list"),
            onClick = { navController.navigate(NavigationRoute.ColorsGrid.route) },
        ) {
            Text("Paginated Color List")
        }
    }
}