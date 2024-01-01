package nl.drbreakalot.lichtstad.ui.program

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import nl.drbreakalot.lichtstad.data.model.DemoProgram
import nl.drbreakalot.lichtstad.ui.component.CenterableScrollableTabRow
import nl.drbreakalot.lichtstad.ui.formatTabDate
import nl.drbreakalot.lichtstad.ui.theme.LichtstadTheme
import nl.drbreakalot.lichtstad.ui.theme.programColorScheme
import org.koin.androidx.compose.navigation.koinNavViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProgramContent(viewModel: ProgramViewModel = koinNavViewModel()) {
    LichtstadTheme(programColorScheme()) {
        Column {
            val days by viewModel.days.collectAsStateWithLifecycle(emptyList())
            val pagerState = rememberPagerState { days.size }
            val scrollScope = rememberCoroutineScope()
            if (days.isEmpty()) return@Column
            CenterableScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier.fillMaxWidth(),
            ) {
                days.forEachIndexed { index, day ->
                    Tab(selected = pagerState.currentPage == index,
                        onClick = { scrollScope.launch { pagerState.animateScrollToPage(index) } },
                        text = { Text(text = day.formatTabDate()) }
                    )
                }
            }
            HorizontalPager(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.Top,
                state = pagerState
            ) { page ->
                ProgramDay(day = days[page])

            }
        }
    }
}


@Composable
private fun ProgramDay(day: LocalDate, viewModel: ProgramViewModel = koinNavViewModel()) {
    val programs by viewModel.programs(day).collectAsStateWithLifecycle(emptyList())
    ProgramList(
        modifier = Modifier.fillMaxHeight(),
        programs = programs
    )
}

@Composable
@Preview(showBackground = true)
private fun PreviewProgramContent() {
    ProgramContent()
}