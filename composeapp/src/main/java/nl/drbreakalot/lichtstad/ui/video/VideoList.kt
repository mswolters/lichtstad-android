package nl.drbreakalot.lichtstad.ui.video

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.drbreakalot.lichtstad.data.model.Video
import org.koin.androidx.compose.navigation.koinNavViewModel

@Composable
fun VideoList(
    videos: List<Video>,
    modifier: Modifier = Modifier,
    viewModel: VideoViewModel = koinNavViewModel()
) {
    //TODO not fixed on tablets/wiiide phones
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(videos, key = { video -> video.id }) {
            VideoItem(video = it, onClick = { viewModel.onClicked(it) })
        }
    }
}