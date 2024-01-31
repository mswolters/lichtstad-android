package nl.drbreakalot.lichtstad.ui.video

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import nl.drbreakalot.lichtstad.ui.component.CenterableScrollableTabRow
import nl.drbreakalot.lichtstad.ui.program.ProgramContent
import nl.drbreakalot.lichtstad.ui.theme.LichtstadTheme
import nl.drbreakalot.lichtstad.ui.theme.videoColorScheme
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.compose.navigation.koinNavViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VideoContent(viewModel: VideoViewModel = koinViewModel()) {
    LichtstadTheme(videoColorScheme()) {
        Column {
            val years by viewModel.years.collectAsStateWithLifecycle(emptyList())
            if (years.isEmpty()) return@Column
            val pagerState = rememberPagerState(initialPage = years.size - 1) { years.size }
            val scrollScope = rememberCoroutineScope()
            CenterableScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier.fillMaxWidth()
            ) {
                years.forEachIndexed { index, year ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { scrollScope.launch { pagerState.animateScrollToPage(index) } },
                        text = { Text(text = "$year") }
                    )
                }
            }
            HorizontalPager(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.Top,
                state = pagerState,
                beyondBoundsPageCount = 1,
            ) { page ->
                VideoYear(year = years[page])
            }
        }
    }
}

@Composable
private fun VideoYear(year: Int, viewModel: VideoViewModel = koinNavViewModel()) {
    val videos by viewModel.videos(year).collectAsStateWithLifecycle(emptyList())
    VideoList(modifier = Modifier.fillMaxHeight(), videos = videos, onVideoClick = { viewModel.onClicked(it) })
}

@Composable
@Preview(showBackground = true)
private fun PreviewProgramContent() {
    ProgramContent()
}