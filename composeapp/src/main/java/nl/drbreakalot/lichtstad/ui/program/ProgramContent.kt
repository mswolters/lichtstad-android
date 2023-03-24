package nl.drbreakalot.lichtstad.ui.program

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nl.drbreakalot.lichtstad.ui.component.CenterableScrollableTabRow
import nl.drbreakalot.lichtstad.ui.theme.LichtstadTheme
import nl.drbreakalot.lichtstad.ui.theme.programColorScheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProgramContent() {
    LichtstadTheme(programColorScheme()) {
        Column {
            val pagerState = rememberPagerState()
            val scrollScope = rememberCoroutineScope()
            CenterableScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier.fillMaxWidth(),
            ) {
                for (tab in 0 until 5) {
                    Tab(selected = pagerState.currentPage == tab,
                        onClick = { scrollScope.launch { pagerState.animateScrollToPage(tab) } },
                        text = { Text(text = "a".repeat(tab + 1)) }
                    )
                }
            }
            HorizontalPager(
                pageCount = 5,
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.Top,
                state = pagerState
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
}

@Composable
@Preview(showBackground = true)
private fun PreviewProgramContent() {
    ProgramContent()
}