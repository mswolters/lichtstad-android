package nl.drbreakalot.lichtstad.ui.program

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import nl.drbreakalot.lichtstad.R
import nl.drbreakalot.lichtstad.data.model.Program
import nl.drbreakalot.lichtstad.ui.component.CustomizableCheckbox
import nl.drbreakalot.lichtstad.ui.formatHoursMinutes
import nl.drbreakalot.lichtstad.ui.noIntrinsicHeight
import nl.drbreakalot.lichtstad.ui.theme.LichtstadTheme
import nl.drbreakalot.lichtstad.ui.theme.programColorScheme
import nl.drbreakalot.lichtstad.data.model.DemoProgram

@Composable
fun ProgramItem(
    program: Program,
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false,
    onFavoriteClick: (Boolean) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !program.description.isNullOrEmpty()) { expanded = !expanded }
            .then(modifier),
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            val startWidth = with(LocalDensity.current) { 48.sp.toDp() }
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = program.timeAsDate.formatHoursMinutes(),
                    modifier = Modifier.width(startWidth),
                    color = MaterialTheme.colorScheme.tertiary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
                Column(Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = program.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    if (!program.location?.name.isNullOrEmpty()) {
                        Row(
                            modifier = Modifier
                                .alpha(0.7f)
                                .padding(top = 2.dp)
                                .height(IntrinsicSize.Min)
                        ) {
                            Icon(
                                painter = LichtstadTheme.iconSet.location(),
                                contentDescription = stringResource(R.string.program_item_location),
                                modifier = Modifier.noIntrinsicHeight(),
                            )
                            Text(
                                text = program.location!!.name,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                CustomizableCheckbox(
                    checked = isFavorite,
                    modifier = Modifier.size(24.dp),
                    onCheckedChanged = onFavoriteClick,
                    checkedImage = LichtstadTheme.iconSet.favorite(true),
                    uncheckedImage = LichtstadTheme.iconSet.favorite(false),
                )
            }
            AnimatedVisibility(visible = expanded) {
                Row(
                    modifier = Modifier
                        .padding(top = 4.dp)
                ) {
                    if (!program.imageUrl.isNullOrEmpty()) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                program.imageUrl,
                                contentScale = ContentScale.Crop
                            ),
                            contentDescription = stringResource(
                                R.string.program_item_image_description_pattern,
                                program.title
                            ),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(startWidth)
                                .aspectRatio(1f)
                        )
                    } else {
                        Spacer(modifier = Modifier.width(startWidth))
                    }
                    if (!program.description.isNullOrEmpty()) {
                        Text(
                            text = program.description,
                            modifier = Modifier.padding(horizontal = 16.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewProgramItem() {
    LichtstadTheme(programColorScheme()) {
        var isFavorite by remember { mutableStateOf(false) }
        ProgramItem(
            program = DemoProgram,
            isFavorite = isFavorite,
            onFavoriteClick = { isFavorite = !isFavorite },
        )
    }
}