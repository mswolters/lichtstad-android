package nl.drbreakalot.lichtstad.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import nl.drbreakalot.lichtstad.ui.theme.Theme

data class NavigationItem(
    val route: String,
    val icon: @Composable () -> Painter,
    val title: @Composable () -> String,
    val theme: Theme,
    val content: @Composable () -> Unit,
)