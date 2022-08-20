package com.move4mobile.lichtstad.ui.main

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.move4mobile.lichtstad.ui.theme.LichtstadTheme
import com.move4mobile.lichtstad.ui.theme.ProgramTheme
import com.move4mobile.lichtstad.ui.i18n.Translations

@ExperimentalMaterial3Api
@Composable
fun MainContent(modifier: Modifier = Modifier) {
    val statusbarColor = MaterialTheme.colorScheme.primary
    val darkStatusIcons = remember { statusbarColor.luminance() > 0.5 }
    val navigationBarColor = NavigationBarDefaults.containerColor
    val darkNavigationIcons = remember { navigationBarColor.luminance() > 0.5 }
    val systemUiController = rememberSystemUiController()
    // In portrait, we let the bottom nav handle the status bar color
    // In landscape, we need a shim or the buttons become invisible
    val navbarShimColor = if (LocalConfiguration.current.orientation == ORIENTATION_PORTRAIT) {
        // Can't do true transparent or android 29+ adds a shim. This is the smallest value that will actually make the bar transparent
        Color(0f, 0f, 0f, 1f / 255f)
    } else if (darkNavigationIcons) {
        // 0.9 is the same alpha as android gives us. No clue if there's anything declared for this, so meh
        Color.White.copy(alpha = 0.9f)
    } else {
        // Same as above, but 0.5. I can't be asked to find out where this is coming from, so here you go
        Color.Black.copy(alpha = 0.5f)
    }
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = darkStatusIcons
        )
        systemUiController.setNavigationBarColor(
            color = navbarShimColor,
            darkIcons = darkNavigationIcons
        )
    }

    Scaffold(
        modifier = modifier,
        topBar = { TopBar() },
        bottomBar = { BottomBar() },
    ) { paddingValues ->
        Surface(Modifier.padding(paddingValues)) {

        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun TopBar() {
    SmallTopAppBar(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)),
        title = { Text("Hai") },
        navigationIcon = {
            Icon(
                painter = LichtstadTheme.iconSet.program(),
                contentDescription = null
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
    )
}

@Composable
private fun BottomBar() {
    Box(
        modifier = Modifier
            // Why 3? Dunno, but it works. Why can't compose handle this for me
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
            .navigationBarsPadding()
    ) {
        NavigationBar(

        ) {
            NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                Icon(
                    painter = LichtstadTheme.iconSet.program(),
                    contentDescription = null
                )
            })
            NavigationBarItem(selected = true, onClick = { /*TODO*/ }, icon = {
                Icon(
                    painter = LichtstadTheme.iconSet.result(),
                    contentDescription = null
                )
            }, label = { Text(Translations.translationSet.menuResults) })
            NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                Icon(
                    painter = LichtstadTheme.iconSet.photo(),
                    contentDescription = null
                )
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Translations {
        ProgramTheme {
            MainContent()
        }
    }
}