package com.move4mobile.lichtstad.ui.program

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.move4mobile.lichtstad.ui.theme.LichtstadTheme
import com.move4mobile.lichtstad.ui.theme.programColorScheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProgramContent() {
    LichtstadTheme(programColorScheme()) {
        HorizontalPager(
            pageCount = 5,
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.Top,
        ) { page ->
            ProgramList(
                modifier = Modifier.fillMaxHeight(),
                programs = List((page + 1) * 4) {
                    DemoProgram.copy(
                        key = it.toString(),
                        description = if (it == 3) null else DemoProgram.description
                    )
                },
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewProgramContent() {
        ProgramContent()
}