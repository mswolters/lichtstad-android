package nl.drbreakalot.lichtstad.ui.photo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import nl.drbreakalot.lichtstad.data.model.Photo
import org.koin.androidx.compose.navigation.koinNavViewModel

@Composable
fun Album(year: String, key: String, viewModel: PhotoViewModel = koinNavViewModel()) {
    val photos by viewModel.photos(year, key).collectAsStateWithLifecycle(emptyList())
    PhotoList(photos = photos, onPhotoClick = { })
}

@Composable
fun PhotoList(photos: List<Photo>, onPhotoClick: (Photo) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(80.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(photos, key = { photo -> photo.key }) {
            PhotoItem(photo = it, onClick = { onPhotoClick(it) })
        }
    }
}

@Composable
fun PhotoItem(photo: Photo, onClick: () -> Unit) {
    SubcomposeAsyncImage(
        model = photo.thumbnailUrl,
        modifier = Modifier
            .aspectRatio(1f)
            .clickable { onClick() },
        contentDescription = "",
        contentScale = ContentScale.Crop,
    )
}