package com.move4mobile.lichtstad.ui.program

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.move4mobile.lichtstad.ui.theme.LichtstadTheme
import com.move4mobile.lichtstad.ui.theme.programColorScheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProgramContent() {
    LichtstadTheme(programColorScheme()) {
        HorizontalPager(
            count = 5,
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.Top
        ) {
            currentPage
            ProgramList(
                programs = List(5) {
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