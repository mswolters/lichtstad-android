package nl.drbreakalot.lichtstad.ui.result

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import nl.drbreakalot.lichtstad.data.model.Result
import nl.drbreakalot.lichtstad.ui.theme.LichtstadTheme
import nl.drbreakalot.lichtstad.ui.theme.resultColorScheme

@Composable
fun ResultItem(
    result: Result,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val startWidth = with(LocalDensity.current) { 48.sp.toDp() }
        if (!result.image.isNullOrEmpty()) {
            AsyncImage(
                model = result.image,
                contentDescription = null,
                modifier = Modifier
                    .width(startWidth)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop,
            )
        } else {
            Spacer(modifier = Modifier.width(startWidth))
        }
        Text(
            text = result.title,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
@Preview
fun ResultItemPreview() {
    LichtstadTheme(resultColorScheme()) {
        ResultItem(
            testResult
        )
    }
}

val testResult = Result(
    "",
    "https://lichtstad-prd.s3.eu-west-1.amazonaws.com/results/2023/Achtkamp-2023-Haka.jpg",
    "Achtkamp 2023",
    ""
)