package com.move4mobile.lichtstad.ui.program

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.move4mobile.lichtstad.model.Program

@Composable
fun ProgramList(programs: List<Program>) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
    ) {
        itemsIndexed(programs, key = { _, program -> program.key }) { index, program ->
            ProgramItem(program)
            if (index < programs.lastIndex) {
                Divider()
            }
        }
    }
}

