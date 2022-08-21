package com.move4mobile.lichtstad.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

data class NavigationItem(
    val route: String,
    val icon: @Composable () -> Painter,
    val title: @Composable () -> String,
    val theme: @Composable (content: @Composable () -> Unit) -> Unit,
    val content: @Composable () -> Unit,
)