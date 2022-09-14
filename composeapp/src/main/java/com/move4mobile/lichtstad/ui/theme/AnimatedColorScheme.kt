package com.move4mobile.lichtstad.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ColorScheme.animated(animationSpec: AnimationSpec<Color> = spring(stiffness = Spring.StiffnessMediumLow)): ColorScheme {
    return ColorScheme(
        primary = primary.animate(animationSpec),
        onPrimary = onPrimary.animate(animationSpec),
        primaryContainer = primaryContainer.animate(animationSpec),
        onPrimaryContainer = onPrimaryContainer.animate(animationSpec),
        inversePrimary = inversePrimary.animate(animationSpec),
        secondary = secondary.animate(animationSpec),
        onSecondary = onSecondary.animate(animationSpec),
        secondaryContainer = secondaryContainer.animate(animationSpec),
        onSecondaryContainer = onSecondaryContainer.animate(animationSpec),
        tertiary = tertiary.animate(animationSpec),
        onTertiary = onTertiary.animate(animationSpec),
        tertiaryContainer = tertiaryContainer.animate(animationSpec),
        onTertiaryContainer = onTertiaryContainer.animate(animationSpec),
        background = background.animate(animationSpec),
        onBackground = onBackground.animate(animationSpec),
        surface = surface.animate(animationSpec),
        onSurface = onSurface.animate(animationSpec),
        surfaceVariant = surfaceVariant.animate(animationSpec),
        onSurfaceVariant = onSurfaceVariant.animate(animationSpec),
        surfaceTint = surfaceTint.animate(animationSpec),
        inverseSurface = inverseSurface.animate(animationSpec),
        inverseOnSurface = inverseOnSurface.animate(animationSpec),
        error = error.animate(animationSpec),
        onError = onError.animate(animationSpec),
        errorContainer = errorContainer.animate(animationSpec),
        onErrorContainer = onErrorContainer.animate(animationSpec),
        outline = outline.animate(animationSpec),
        outlineVariant = outlineVariant.animate(animationSpec),
        scrim = scrim.animate(animationSpec),
    )
}

@Composable
private fun Color.animate(
    animationSpec: AnimationSpec<Color>,
): Color = animateColorAsState(targetValue = this, animationSpec = animationSpec).value