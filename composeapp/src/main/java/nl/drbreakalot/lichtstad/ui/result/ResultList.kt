package nl.drbreakalot.lichtstad.ui.result

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.drbreakalot.lichtstad.data.model.Result

@Composable
fun ResultList(results: List<Result>, modifier: Modifier = Modifier) {
    // Would love to use contentPadding on the column, but that causes the ripple to not
    // extend to the edge of the screen, so it's on each item instead.
    LazyColumn(
        modifier = modifier,
    ) {
        itemsIndexed(results, key = { _, result -> result.key }) { index, result ->
            ResultItem(result, Modifier.padding(paddingForIndex(index, results.size)))
            if (index < results.lastIndex) {
                Divider()
            }
        }
    }
}

fun paddingForIndex(index: Int, numItems: Int): PaddingValues {
    val topPadding = if (index == 0) 8.dp else 0.dp
    val bottomPadding = if (index == numItems - 1) 8.dp else 0.dp
    return PaddingValues(start = 8.dp, end = 8.dp, top = topPadding, bottom = bottomPadding)
}