package com.move4mobile.lichtstad.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import blend.Blend
import com.move4mobile.lichtstad.R
import scheme.Scheme

@Composable
@Stable
fun lichtstadColorScheme(
    primary: Color,
    harmonize: Color = primary,
    dark: Boolean = isSystemInDarkTheme()
): ColorScheme {
    val blended = Blend.harmonize(primary.toArgb(), harmonize.toArgb())
    val scheme = if (dark) {
        Scheme.dark(blended)
    } else {
        Scheme.light(blended)
    }
    return ColorScheme(
        primary = Color(scheme.primary),
        onPrimary = Color(scheme.onPrimary),
        primaryContainer = Color(scheme.primaryContainer),
        onPrimaryContainer = Color(scheme.onPrimaryContainer),
        secondary = Color(scheme.secondary),
        onSecondary = Color(scheme.onSecondary),
        secondaryContainer = Color(scheme.secondaryContainer),
        onSecondaryContainer = Color(scheme.onSecondaryContainer),
        tertiary = Color(scheme.tertiary),
        onTertiary = Color(scheme.onTertiary),
        tertiaryContainer = Color(scheme.tertiaryContainer),
        onTertiaryContainer = Color(scheme.onTertiaryContainer),
        inversePrimary = Color(scheme.inversePrimary),
        background = Color(scheme.background),
        onBackground = Color(scheme.onBackground),
        surface = Color(scheme.surface),
        onSurface = Color(scheme.onSurface),
        surfaceVariant = Color(scheme.surfaceVariant),
        onSurfaceVariant = Color(scheme.onSurfaceVariant),
        surfaceTint = Color(scheme.primary),
        inverseSurface = Color(scheme.inverseSurface),
        inverseOnSurface = Color(scheme.inverseOnSurface),
        error = Color(scheme.error),
        onError = Color(scheme.onError),
        errorContainer = Color(scheme.errorContainer),
        onErrorContainer = Color(scheme.onErrorContainer),
        outline = Color(scheme.outline),
        outlineVariant = Color(scheme.outlineVariant),
        scrim = Color(scheme.scrim),
    )
}

@Composable
fun programColorScheme(): ColorScheme {
    val baseScheme = lichtstadColorScheme(
        colorResource(id = R.color.primary_program)
    )
    val secondaryScheme = lichtstadColorScheme(
        colorResource(id = R.color.secondary_program)
    )
    return baseScheme.copy(
        tertiary = secondaryScheme.primary,
        onTertiary = secondaryScheme.onPrimary,
        tertiaryContainer = secondaryScheme.primaryContainer,
        onTertiaryContainer = secondaryScheme.onPrimaryContainer
    )
}

@Composable
fun resultColorScheme() = lichtstadColorScheme(
    primary = colorResource(id = R.color.primary_result),
    harmonize = colorResource(id = R.color.primary_program)
)

@Composable
fun photoColorScheme() = lichtstadColorScheme(
    primary = colorResource(id = R.color.primary_photo),
    harmonize = colorResource(id = R.color.primary_program)
)

@Composable
fun videoColorScheme() = lichtstadColorScheme(
    primary = colorResource(id = R.color.primary_video),
    harmonize = colorResource(id = R.color.primary_program)
)

@Composable
fun mapColorScheme() = lichtstadColorScheme(
    primary = colorResource(id = R.color.primary_map),
    harmonize = colorResource(id = R.color.primary_program)
)

