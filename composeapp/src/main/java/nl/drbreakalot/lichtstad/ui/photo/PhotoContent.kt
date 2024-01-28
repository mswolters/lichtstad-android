package nl.drbreakalot.lichtstad.ui.photo

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
import nl.drbreakalot.lichtstad.ui.theme.photoColorScheme
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.compose.navigation.koinNavViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoContent(viewModel: PhotoViewModel = koinViewModel()) {
    LichtstadTheme(photoColorScheme()) {
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
                PhotoYear(year = years[page])
            }
        }
    }
}

@Composable
private fun PhotoYear(year: Int, viewModel: PhotoViewModel = koinNavViewModel()) {
    val photos by viewModel.albums(year).collectAsStateWithLifecycle(emptyList())
    AlbumList(albums = photos, modifier = Modifier.fillMaxHeight()) {
        TODO()
    }
}