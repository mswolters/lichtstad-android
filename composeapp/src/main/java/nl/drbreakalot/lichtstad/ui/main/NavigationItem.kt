package nl.drbreakalot.lichtstad.ui.main

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import nl.drbreakalot.lichtstad.ui.theme.Theme

data class NavigationItem(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val icon: @Composable () -> Painter,
    val title: @Composable () -> String,
    val theme: Theme,
    val content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
    val children: List<NavigationItem> = emptyList()
) {

    fun generateGraph(navGraphBuilder: NavGraphBuilder, parentRoute: String = "") {
        with(navGraphBuilder) {
            val baseRoute = if (parentRoute.isEmpty()) {
                this@NavigationItem.route
            } else {
                "$parentRoute/${this@NavigationItem.route}"
            }
            if (children.isEmpty()) {
                composable(baseRoute, arguments = arguments) { backstackEntry ->
                    content(backstackEntry)
                }
            } else {
                navigation("$baseRoute/", baseRoute) {
                    composable("$baseRoute/", arguments = arguments) { backstackEntry ->
                        content(backstackEntry)
                    }
                    children.forEach { it.generateGraph(this) }
                }
            }
        }
    }
}