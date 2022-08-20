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
    var program by mutableStateOf(program)
        internal set
    var result by mutableStateOf(result)
        internal set
    var photo by mutableStateOf(photo)
        internal set
    var video by mutableStateOf(video)
        internal set
    var map by mutableStateOf(map)
        internal set
    var favorite by mutableStateOf(favorite)
        internal set
    var share by mutableStateOf(share)
        internal set
    var play by mutableStateOf(play)
        internal set

    fun copy(
        program: @Composable () -> Painter = this.program,
        result: @Composable () -> Painter = this.result,
        photo: @Composable () -> Painter = this.photo,
        video: @Composable () -> Painter = this.video,
        map: @Composable () -> Painter = this.map,
        favorite: @Composable (selected: Boolean) -> Painter = this.favorite,
        share: @Composable () -> Painter = this.share,
        play: @Composable () -> Painter = this.play
    ): IconSet = IconSet (
        program = program,
        result = result,
        photo = photo,
        video = video,
        map = map,
        favorite = favorite,
        share = share,
        play = play,
    )

    override fun toString(): String {
        return "IconSet(program=$program, result=$result, photo=$photo, video=$video, map=$map, favorite=$favorite, share=$share, play=$play)"
    }
}

fun lichtstadIconSet (
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
): IconSet = IconSet (
    program = program,
    result = result,
    photo = photo,
    video = video,
    map = map,
    favorite = favorite,
    share = share,
    play = play,
)

internal fun IconSet.updateIconSetFrom(other: IconSet) {
    program = other.program
    result = other.result
    photo = other.photo
    video = other.video
    map = other.map
    favorite = other.favorite
    share = other.share
    play = other.play
}

internal val LocalIconSet = staticCompositionLocalOf { lichtstadIconSet() }