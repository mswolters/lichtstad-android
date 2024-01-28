package nl.drbreakalot.lichtstad.ui.photo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import nl.drbreakalot.lichtstad.data.model.Album
import nl.drbreakalot.lichtstad.data.model.DemoAlbum
import nl.drbreakalot.lichtstad.ui.theme.LichtstadTheme
import nl.drbreakalot.lichtstad.ui.theme.photoColorScheme

@Composable
fun AlbumItem(album: Album, modifier: Modifier = Modifier, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Column(modifier.clickable { onClick() }) {
            Box {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(album.thumbnailSize.width.toFloat() / album.thumbnailSize.height.toFloat()),
                    model = album.thumbnailUrl,
                    contentDescription = "",
                    loading = {},
                    contentScale = ContentScale.Crop
                )
                Text(text = "${album.photoCount}",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .shadow(elevation = 2.dp, shape = RoundedCornerShape(50f))
                        .clip(RoundedCornerShape(50f))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(4.dp),
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            Text(
                text = album.title,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.titleSmall
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun AlbumItemPreview() {
    LichtstadTheme(photoColorScheme()) {
        AlbumItem(album = DemoAlbum, onClick = {})
    }
}
