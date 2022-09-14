package com.move4mobile.lichtstad.ui

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints

@Stable
fun Modifier.noIntrinsicHeight(): Modifier = layout { measurable, constraints ->
    if (constraints.maxHeight == Constraints.Infinity) {
        layout(0, 0) {}
    } else {
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
}