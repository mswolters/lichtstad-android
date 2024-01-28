package nl.drbreakalot.lichtstad.ui.result

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import nl.drbreakalot.lichtstad.ui.component.CenterableScrollableTabRow
import nl.drbreakalot.lichtstad.ui.theme.LichtstadTheme
import nl.drbreakalot.lichtstad.ui.theme.resultColorScheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ResultContent(viewModel: ResultViewModel = koinViewModel()) {
    LichtstadTheme(resultColorScheme()) {
        Column {
            val years by viewModel.years.collectAsStateWithLifecycle(emptyList())
            if (years.isEmpty()) return@Column
            val initialPage = years.size - 1
            val pagerState = rememberPagerState(initialPage = initialPage) { years.size }
            val scrollScope = rememberCoroutineScope()

            CenterableScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier.fillMaxWidth()
            ) {
                years.forEachIndexed { index, year ->
                    Tab(selected = pagerState.currentPage == index,
                        onClick = { scrollScope.launch { pagerState.animateScrollToPage(index) } },
                        text = { Text(text = "$year") }
                    )
                }
            }
            HorizontalPager(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.Top,
                state = pagerState
            ) { page ->
                val results by viewModel.results(years[page]).collectAsStateWithLifecycle(emptyList())
                ResultList(
                    results = results,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}