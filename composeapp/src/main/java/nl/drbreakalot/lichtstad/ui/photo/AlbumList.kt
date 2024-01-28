package nl.drbreakalot.lichtstad.ui.photo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.drbreakalot.lichtstad.data.model.Album

@Composable
fun AlbumList(albums: List<Album>, modifier: Modifier = Modifier, onAlbumClick: (Album) -> Unit) {
    //TODO not fixed on tablets/wiiide phones
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(albums, key = { album -> album.key }) {
            AlbumItem(album = it, onClick = { onAlbumClick(it) })
        }
    }
}

