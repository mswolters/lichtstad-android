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
        theme = Theme.Program,
        content = {  }
    ),
    NavigationItem(
        route = ROUTE_RESULTS,
        title = { Translations.translationSet.menuResults },
        icon = { LichtstadTheme.iconSet.result() },
        theme = Theme.Result,
        content = {  }
    ),
    NavigationItem(
        route = ROUTE_PHOTOS,
        title = { Translations.translationSet.menuPhotos },
        icon = { LichtstadTheme.iconSet.photo() },
        theme = Theme.Photo,
        content = {  }
    ),
    NavigationItem(
        route = ROUTE_VIDEOS,
        title = { Translations.translationSet.menuVideos },
        icon = { LichtstadTheme.iconSet.video() },
        theme = Theme.Video,
        content = {  }
    ),
    NavigationItem(
        route = ROUTE_MAP,
        title = { Translations.translationSet.menuMap },
        icon = { LichtstadTheme.iconSet.map() },
        theme = Theme.Map,
        content = {  }
    ),
)