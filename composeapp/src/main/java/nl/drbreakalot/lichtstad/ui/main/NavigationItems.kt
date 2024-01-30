package nl.drbreakalot.lichtstad.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kotlinx.coroutines.launch
import nl.drbreakalot.lichtstad.R
import nl.drbreakalot.lichtstad.data.service.NavigationService
import nl.drbreakalot.lichtstad.ui.map.MapContent
import nl.drbreakalot.lichtstad.ui.photo.Album
import nl.drbreakalot.lichtstad.ui.photo.PhotoContent
import nl.drbreakalot.lichtstad.ui.program.ProgramContent
import nl.drbreakalot.lichtstad.ui.result.ResultContent
import nl.drbreakalot.lichtstad.ui.theme.LichtstadTheme
import nl.drbreakalot.lichtstad.ui.theme.Theme
import nl.drbreakalot.lichtstad.ui.video.VideoContent

const val ROUTE_PROGRAM = "program"
const val ROUTE_RESULTS = "results"
const val ROUTE_PHOTOS = "photos"
const val ROUTE_VIDEOS = "videos"
const val ROUTE_MAP = "map"
fun routeAlbum(year: String, key: String) = "photos/$year/$key"

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
        content = { ResultContent() }
    ),
    NavigationItem(
        route = ROUTE_PHOTOS,
        title = { stringResource(R.string.menu_photos) },
        icon = { LichtstadTheme.iconSet.photo() },
        theme = Theme.Photo,
        content = { PhotoContent() },
        children = listOf(
            NavigationItem(
                route = "photos/{year}/{key}",
                arguments = listOf(
                    navArgument("year") { type = NavType.StringType },
                    navArgument("key") { type = NavType.StringType }
                ),
                title = { stringResource(R.string.menu_photos) },
                icon = { LichtstadTheme.iconSet.photo() },
                theme = Theme.Photo,
                content = { backstackEntry ->
                    val year = backstackEntry.arguments?.getString("year")!!
                    val key = backstackEntry.arguments?.getString("key")!!
                    Album(year, key)}
            )
        )
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
        content = { MapContent() }
    ),
)

class NavigationViewModel(private val navigationService: NavigationService) : ViewModel() {

    var activeNavigationItem: NavigationItem by mutableStateOf(NAVIGATION_ITEMS[0])
    val navigation = navigationService.navigation
    fun navigate(route: String, navOptions: NavOptions? = null) {
        viewModelScope.launch {
            navigationService.navigate(route, navOptions)
        }
    }

}