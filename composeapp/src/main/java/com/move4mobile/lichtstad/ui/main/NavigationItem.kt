package com.move4mobile.lichtstad.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.move4mobile.lichtstad.ui.theme.Theme

data class NavigationItem(
    val route: String,
    val icon: @Composable () -> Painter,
    val title: @Composable () -> String,
    val theme: Theme,
    val content: @Composable () -> Unit,
)