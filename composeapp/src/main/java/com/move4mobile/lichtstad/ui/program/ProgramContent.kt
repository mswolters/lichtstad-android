package com.move4mobile.lichtstad.ui.program

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.move4mobile.lichtstad.ui.theme.LichtstadTheme
import com.move4mobile.lichtstad.ui.theme.programColorScheme

@Composable
fun ProgramContent() {
    ProgramList(programs = List(5) { DemoProgram.copy(key = it.toString(), description = if (it == 3) null else DemoProgram.description) })
}

@Composable
@Preview(showBackground = true)
private fun PreviewProgramContent() {
    LichtstadTheme(programColorScheme()) {
        ProgramContent()
    }
}