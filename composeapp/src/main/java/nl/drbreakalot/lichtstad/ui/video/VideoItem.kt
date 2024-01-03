package nl.drbreakalot.lichtstad.ui.video

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import nl.drbreakalot.lichtstad.R
import nl.drbreakalot.lichtstad.data.model.DemoVideo
import nl.drbreakalot.lichtstad.data.model.Video
import nl.drbreakalot.lichtstad.ui.theme.LichtstadTheme
import nl.drbreakalot.lichtstad.ui.theme.videoColorScheme
import org.koin.androidx.compose.navigation.koinNavViewModel

@Composable
fun VideoItem(
    video: Video,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Column(modifier = Modifier.clickable { onClick() }) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                painter = rememberAsyncImagePainter(
                    video.thumbnails["high"],
                    contentScale = ContentScale.Crop
                ),
                contentDescription = stringResource(
                    R.string.video_item_image_description_pattern,
                    video.title
                ),
                contentScale = ContentScale.Crop
            )
            Text(
                text = video.title,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewVideoItem() {
    LichtstadTheme(videoColorScheme()) {
        VideoItem(
            video = DemoVideo,
            onClick = {}
        )
    }
}