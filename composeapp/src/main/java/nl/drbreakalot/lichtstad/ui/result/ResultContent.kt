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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import nl.drbreakalot.lichtstad.ui.component.CenterableScrollableTabRow
import nl.drbreakalot.lichtstad.ui.theme.LichtstadTheme
import nl.drbreakalot.lichtstad.ui.theme.resultColorScheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ResultContent() {
    LichtstadTheme(resultColorScheme()) {
        Column {
            val years = listOf(2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023)
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
                        text = { Text(text = year.toString()) }
                    )
                }
            }
            HorizontalPager(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.Top,
                state = pagerState
            ) { page ->
                ResultList(
                    results = listOf(testResult.copy(key = "1"), testResult.copy(key = "2")),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}