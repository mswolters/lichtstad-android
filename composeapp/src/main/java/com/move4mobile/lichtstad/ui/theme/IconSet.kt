package com.move4mobile.lichtstad.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter

@Stable
class IconSet(
    program: @Composable () -> Painter,
    result: @Composable () -> Painter,
    photo: @Composable () -> Painter,
    video: @Composable () -> Painter,
    map: @Composable () -> Painter,
    favorite: @Composable (selected: Boolean) -> Painter,
    share: @Composable () -> Painter,
    play: @Composable () -> Painter,
) {
    val program by mutableStateOf(program)
    val result by mutableStateOf(result)
    val photo by mutableStateOf(photo)
    val video by mutableStateOf(video)
    val map by mutableStateOf(map)
    val favorite by mutableStateOf(favorite)
    val share by mutableStateOf(share)
    val play by mutableStateOf(play)

    override fun toString(): String {
        return "IconSet(" +
                "program=$program, " +
                "result=$result, " +
                "photo=$photo, " +
                "video=$video, " +
                "map=$map, " +
                "favorite=$favorite, " +
                "share=$share, " +
                "play=$play" +
                ")"
    }
}

fun lichtstadIconSet(
    program: @Composable () -> Painter = { rememberVectorPainter(Icons.Outlined.Assignment) },
    result: @Composable () -> Painter = { rememberVectorPainter(Icons.Filled.EmojiEvents) },
    photo: @Composable () -> Painter = { rememberVectorPainter(Icons.Filled.PhotoLibrary) },
    video: @Composable () -> Painter = { rememberVectorPainter(Icons.Filled.VideoLibrary) },
    map: @Composable () -> Painter = { rememberVectorPainter(Icons.Filled.Map) },
    favorite: @Composable (selected: Boolean) -> Painter = { selected ->
        if (selected) rememberVectorPainter(Icons.Filled.Favorite)
        else rememberVectorPainter(Icons.Filled.FavoriteBorder)
    },
    share: @Composable () -> Painter = { rememberVectorPainter(Icons.Filled.Share) },
    play: @Composable () -> Painter = { rememberVectorPainter(Icons.Filled.PlayArrow) }
): IconSet = IconSet(
    program = program,
    result = result,
    photo = photo,
    video = video,
    map = map,
    favorite = favorite,
    share = share,
    play = play,
)

internal val LocalIconSet = staticCompositionLocalOf { lichtstadIconSet() }