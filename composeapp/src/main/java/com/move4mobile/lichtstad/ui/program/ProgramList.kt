package com.move4mobile.lichtstad.ui.program

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.move4mobile.lichtstad.model.Program

@Composable
fun ProgramList(programs: List<Program>, modifier: Modifier = Modifier) {
    // Would love to use contentPadding on the column, but that causes the ripple to not
    // extend to the edge of the screen, so it's on each item instead.
    LazyColumn(
        modifier = modifier,
    ) {
        itemsIndexed(programs, key = { _, program -> program.key }) { index, program ->
            ProgramItem(program, Modifier.padding(paddingForIndex(index, programs.size)))
            if (index < programs.lastIndex) {
                Divider()
            }
        }
    }
}

private fun paddingForIndex(index: Int, numItems: Int): PaddingValues {
    val topPadding = if (index == 0) 8.dp else 0.dp
    val bottomPadding = if (index == numItems - 1) 8.dp else 0.dp
    return PaddingValues(start = 8.dp, end = 8.dp, top = topPadding, bottom = bottomPadding)
}

