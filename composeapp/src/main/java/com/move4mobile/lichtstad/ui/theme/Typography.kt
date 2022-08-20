package com.move4mobile.lichtstad.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.move4mobile.lichtstad.R

val enzoOTFontFamily = FontFamily(
    Font(R.font.enzoot_bold, FontWeight.Bold),
    Font(R.font.enzoot_medi, FontWeight.Normal),
    Font(R.font.enzoot_light, FontWeight.Thin),
)

private val defaultTypography = Typography()
// Makes me cry as well, but can't find a better way to override the font in everything otherwise
val Typography = Typography(
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = enzoOTFontFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = enzoOTFontFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = enzoOTFontFamily),
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = enzoOTFontFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = enzoOTFontFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = enzoOTFontFamily),
    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = enzoOTFontFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = enzoOTFontFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = enzoOTFontFamily),
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = enzoOTFontFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = enzoOTFontFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = enzoOTFontFamily),
    titleLarge = defaultTypography.titleLarge.copy(fontFamily = enzoOTFontFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = enzoOTFontFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = enzoOTFontFamily),
)