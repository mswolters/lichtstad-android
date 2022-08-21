package com.move4mobile.lichtstad.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel

@Preview(name = "Light mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class PreviewThemes

object LichtstadTheme {

    val iconSet: IconSet
        @Composable
        @ReadOnlyComposable
        get() = LocalIconSet.current

}

class ThemeViewModel : ViewModel() {

    var activeTheme: Theme by mutableStateOf(Theme.values()[0])

}

enum class Theme(val colorScheme: @Composable () -> ColorScheme) {
    Program({ programColorScheme() }),
    Result({ resultColorScheme() }),
    Photo({ photoColorScheme() }),
    Video({ videoColorScheme() }),
    Map({ mapColorScheme() }),
}

@Composable
fun LichtstadTheme(
    colorScheme: ColorScheme,
    iconSet: IconSet = lichtstadIconSet(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalIconSet provides iconSet
    ) {
        MaterialTheme(
            colorScheme = colorScheme.animated(),
            typography = Typography,
            content = content
        )
    }
}

@Composable
fun LichtstadTheme(
    themeViewModel: ThemeViewModel = viewModel(),
    iconSet: IconSet = lichtstadIconSet(),
    content: @Composable () -> Unit
) {
    LichtstadTheme(
        colorScheme = themeViewModel.activeTheme.colorScheme(),
        iconSet = iconSet,
        content = content
    )
}

@Composable
@PreviewThemes
private fun ProgramThemePreview() {
    LichtstadTheme(colorScheme = programColorScheme()) {
        PreviewBoxes()
    }
}

@Composable
@PreviewThemes
private fun ResultThemePreview() {
    LichtstadTheme(colorScheme = resultColorScheme()) {
        PreviewBoxes()
    }
}

@Composable
@PreviewThemes
private fun PhotoThemePreview() {
    LichtstadTheme(colorScheme = photoColorScheme()) {
        PreviewBoxes()
    }
}

@Composable
@PreviewThemes
private fun VideoThemePreview() {
    LichtstadTheme(colorScheme = videoColorScheme()) {
        PreviewBoxes()
    }
}

@Composable
@PreviewThemes
private fun MapThemePreview() {
    LichtstadTheme(colorScheme = mapColorScheme()) {
        PreviewBoxes()
    }
}

@Composable
private fun PreviewBoxes() {
    LazyVerticalGrid(columns = GridCells.Fixed(4)) {
        item {
            ColorBox(
                name = "Primary",
                color = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.onPrimary
            )
        }
        item {
            ColorBox(
                name = "On Primary",
                color = MaterialTheme.colorScheme.onPrimary,
                textColor = MaterialTheme.colorScheme.primary
            )
        }
        item {
            ColorBox(
                name = "Primary Container",
                color = MaterialTheme.colorScheme.primaryContainer,
                textColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        item {
            ColorBox(
                name = "On Primary Container",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textColor = MaterialTheme.colorScheme.onPrimary
            )
        }
        item {
            ColorBox(
                name = "Secondary",
                color = MaterialTheme.colorScheme.secondary,
                textColor = MaterialTheme.colorScheme.onSecondary
            )
        }
        item {
            ColorBox(
                name = "On Secondary",
                color = MaterialTheme.colorScheme.onSecondary,
                textColor = MaterialTheme.colorScheme.secondary
            )
        }
        item {
            ColorBox(
                name = "Secondary Container",
                color = MaterialTheme.colorScheme.secondaryContainer,
                textColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        item {
            ColorBox(
                name = "On Secondary Container",
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textColor = MaterialTheme.colorScheme.onSecondary
            )
        }
        item {
            ColorBox(
                name = "Tertiary",
                color = MaterialTheme.colorScheme.tertiary,
                textColor = MaterialTheme.colorScheme.onTertiary
            )
        }
        item {
            ColorBox(
                name = "On Tertiary",
                color = MaterialTheme.colorScheme.onTertiary,
                textColor = MaterialTheme.colorScheme.tertiary
            )
        }
        item {
            ColorBox(
                name = "Tertiary Container",
                color = MaterialTheme.colorScheme.tertiaryContainer,
                textColor = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
        item {
            ColorBox(
                name = "On Tertiary Container",
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                textColor = MaterialTheme.colorScheme.onTertiary
            )
        }
        item {
            ColorBox(
                name = "Error",
                color = MaterialTheme.colorScheme.error,
                textColor = MaterialTheme.colorScheme.onError
            )
        }
        item {
            ColorBox(
                name = "On Error",
                color = MaterialTheme.colorScheme.onError,
                textColor = MaterialTheme.colorScheme.error
            )
        }
        item {
            ColorBox(
                name = "Error Container",
                color = MaterialTheme.colorScheme.errorContainer,
                textColor = MaterialTheme.colorScheme.onErrorContainer
            )
        }
        item {
            ColorBox(
                name = "On Error Container",
                color = MaterialTheme.colorScheme.onErrorContainer,
                textColor = MaterialTheme.colorScheme.onError
            )
        }
        item {
            ColorBox(
                name = "Background",
                color = MaterialTheme.colorScheme.background,
                textColor = MaterialTheme.colorScheme.onBackground
            )
        }
        item {
            ColorBox(
                name = "On Background",
                color = MaterialTheme.colorScheme.onBackground,
                textColor = MaterialTheme.colorScheme.background
            )
        }
        item {
            ColorBox(
                name = "Surface",
                color = MaterialTheme.colorScheme.surface,
                textColor = MaterialTheme.colorScheme.onSurface
            )
        }
        item {
            ColorBox(
                name = "On Surface",
                color = MaterialTheme.colorScheme.onSurface,
                textColor = MaterialTheme.colorScheme.surface
            )
        }
        item {
            ColorBox(
                name = "Surface Variant",
                color = MaterialTheme.colorScheme.surfaceVariant,
                textColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        item {
            ColorBox(
                name = "On Surface Variant",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
        item {
            ColorBox(
                name = "Outline",
                color = MaterialTheme.colorScheme.outline,
                textColor = MaterialTheme.colorScheme.outlineVariant
            )
        }
        item {
            ColorBox(
                name = "Outline Variant",
                color = MaterialTheme.colorScheme.outlineVariant,
                textColor = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Composable
private fun ColorBox(name: String, color: Color, textColor: Color) {
    Box(
        modifier = Modifier
            .height(100.dp)
            .background(color)
            .padding(8.dp)
    ) {
        Text(text = name, color = textColor, fontSize = 12.sp)
    }
}