package net.composegridperformance.navigation

sealed class NavigationRoute(var route: String) {
    data object Menu : NavigationRoute("menu")
    data object ImagesGrid : NavigationRoute("images_grid")
    data object ColorsGrid : NavigationRoute("colors_grid")
}