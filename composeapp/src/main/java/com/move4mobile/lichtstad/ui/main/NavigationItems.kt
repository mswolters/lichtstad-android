package com.move4mobile.lichtstad.ui.main

import com.move4mobile.lichtstad.ui.i18n.Translations
import com.move4mobile.lichtstad.ui.theme.*

const val ROUTE_PROGRAM = "program"
const val ROUTE_RESULTS = "results"
const val ROUTE_PHOTOS = "photos"
const val ROUTE_VIDEOS = "videos"
const val ROUTE_MAP = "map"

const val ROUTE_DEFAULT = ROUTE_PROGRAM
val NAVIGATION_ITEMS = listOf(
    NavigationItem(
        route = ROUTE_PROGRAM,
        title = { Translations.translationSet.menuProgram },
        icon = { LichtstadTheme.iconSet.program() },
        theme = { content -> ProgramTheme(content) },
        content = {  }
    ),
    NavigationItem(
        route = ROUTE_RESULTS,
        title = { Translations.translationSet.menuResults },
        icon = { LichtstadTheme.iconSet.result() },
        theme = { content -> ResultTheme(content) },
        content = {  }
    ),
    NavigationItem(
        route = ROUTE_PHOTOS,
        title = { Translations.translationSet.menuPhotos },
        icon = { LichtstadTheme.iconSet.photo() },
        theme = { content -> PhotoTheme(content) },
        content = {  }
    ),
    NavigationItem(
        route = ROUTE_VIDEOS,
        title = { Translations.translationSet.menuVideos },
        icon = { LichtstadTheme.iconSet.video() },
        theme = { content -> VideoTheme(content) },
        content = {  }
    ),
    NavigationItem(
        route = ROUTE_MAP,
        title = { Translations.translationSet.menuMap },
        icon = { LichtstadTheme.iconSet.map() },
        theme = { content -> MapTheme(content) },
        content = {  }
    ),
)