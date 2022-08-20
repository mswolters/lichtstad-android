package com.move4mobile.lichtstad.ui.i18n

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.move4mobile.lichtstad.R
import java.util.Locale

@Stable
class TranslationSet(
    appName: String,
    menuProgram: String,
    menuResults: String,
    menuPhotos: String,
    menuVideos: String,
    menuMap: String
) {
    var appName by mutableStateOf(appName)
        internal set
    var menuProgram by mutableStateOf(menuProgram)
        internal set
    var menuResults by mutableStateOf(menuResults)
        internal set
    var menuPhotos by mutableStateOf(menuPhotos)
        internal set
    var menuVideos by mutableStateOf(menuVideos)
        internal set
    var menuMap by mutableStateOf(menuMap)
        internal set

    fun copy(
        appName: String = this.appName,
        menuProgram: String = this.menuProgram,
        menuResults: String = this.menuResults,
        menuPhotos: String = this.menuPhotos,
        menuVideos: String = this.menuVideos,
        menuMap: String = this.menuMap
    ): TranslationSet = TranslationSet(
        appName = appName,
        menuProgram = menuProgram,
        menuResults = menuResults,
        menuPhotos = menuPhotos,
        menuVideos = menuVideos,
        menuMap = menuMap
    )

    override fun toString(): String {
        return "Translations(appName=$appName, " +
                "menuProgram=$menuProgram, " +
                "menuResults=$menuResults, " +
                "menuPhotos=$menuPhotos, " +
                "menuVideos=$menuVideos, " +
                "menuMap=$menuMap" +
                ")"
    }
}

private fun defaultTranslations(
    appName: String = "\$appName",
    menuProgram: String = "\$menuProgram",
    menuResults: String = "\$menuResults",
    menuPhotos: String = "\$menuPhotos",
    menuVideos: String = "\$menuVideos",
    menuMap: String = "\$menuMap"
): TranslationSet = TranslationSet(
    appName = appName,
    menuProgram = menuProgram,
    menuResults = menuResults,
    menuPhotos = menuPhotos,
    menuVideos = menuVideos,
    menuMap = menuMap
)

internal fun TranslationSet.updateTranslationsFrom(other: TranslationSet) {
    appName = other.appName
    menuProgram = other.menuProgram
    menuResults = other.menuResults
    menuPhotos = other.menuPhotos
    menuVideos = other.menuVideos
    menuMap = other.menuMap
}

internal val LocalTranslations = staticCompositionLocalOf { defaultTranslations() }

object Translations {

    val translationSet: TranslationSet
        @Composable
        @ReadOnlyComposable
        get() = LocalTranslations.current

}

@Composable
fun Translations(content: @Composable () -> Unit) {
    val context = LocalContext.current
    // Yikes, can't find a better way to separate translations and locale
    val rememberedTranslationSet =
        remember(Locale.getDefault().displayCountry, Locale.getDefault().language) {
            TranslationSet(
                appName = context.getString(R.string.app_name),
                menuProgram = context.getString(R.string.menu_program),
                menuResults = context.getString(R.string.menu_results),
                menuPhotos = context.getString(R.string.menu_photos),
                menuVideos = context.getString(R.string.menu_videos),
                menuMap = context.getString(R.string.menu_map)
            )
        }
    CompositionLocalProvider(
        LocalTranslations provides rememberedTranslationSet,
        content = content
    )
}