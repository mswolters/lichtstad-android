package nl.drbreakalot.lichtstad.ui.main

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import nl.drbreakalot.lichtstad.R
import nl.drbreakalot.lichtstad.ui.program.ProgramContent
import nl.drbreakalot.lichtstad.ui.theme.LichtstadTheme
import nl.drbreakalot.lichtstad.ui.theme.Theme
import nl.drbreakalot.lichtstad.ui.video.VideoContent

const val ROUTE_PROGRAM = "program"
const val ROUTE_RESULTS = "results"
const val ROUTE_PHOTOS = "photos"
const val ROUTE_VIDEOS = "videos"
const val ROUTE_MAP = "map"

const val ROUTE_DEFAULT = ROUTE_PROGRAM
val NAVIGATION_ITEMS = listOf(
    NavigationItem(
        route = ROUTE_PROGRAM,
        title = { stringResource(R.string.menu_program) },
        icon = { LichtstadTheme.iconSet.program() },
        theme = Theme.Program,
        content = { ProgramContent() }
    ),
    NavigationItem(
        route = ROUTE_RESULTS,
        title = { stringResource(R.string.menu_results) },
        icon = { LichtstadTheme.iconSet.result() },
        theme = Theme.Result,
        content = {  }
    ),
    NavigationItem(
        route = ROUTE_PHOTOS,
        title = { stringResource(R.string.menu_photos) },
        icon = { LichtstadTheme.iconSet.photo() },
        theme = Theme.Photo,
        content = {  }
    ),
    NavigationItem(
        route = ROUTE_VIDEOS,
        title = { stringResource(R.string.menu_videos) },
        icon = { LichtstadTheme.iconSet.video() },
        theme = Theme.Video,
        content = { VideoContent() }
    ),
    NavigationItem(
        route = ROUTE_MAP,
        title = { stringResource(R.string.menu_map) },
        icon = { LichtstadTheme.iconSet.map() },
        theme = Theme.Map,
        content = {  }
    ),
)

class NavigationViewModel : ViewModel() {

    var activeNavigationItem: NavigationItem by mutableStateOf(NAVIGATION_ITEMS[0])

}