package com.move4mobile.lichtstad.ui.main

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.move4mobile.lichtstad.ui.component.FlexibleNavigationBar
import com.move4mobile.lichtstad.ui.i18n.Translations
import com.move4mobile.lichtstad.ui.theme.LichtstadTheme

@ExperimentalMaterial3Api
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    navigationViewModel: NavigationViewModel = viewModel(),
    navigationItems: List<NavigationItem> = NAVIGATION_ITEMS
) {
    val navController = rememberNavController()
    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { controller, destination, bundle ->
            navigationViewModel.activeNavigationItem =
                navigationItems.single { it.route == destination.route }
        }
    }
    LichtstadTheme(
        navigationViewModel = navigationViewModel,
    ) {
        TintSystemBars()

        Scaffold(
            modifier = modifier,
            topBar = { TopBar() },
            bottomBar = {
                BottomBar(
                    navigationItems = navigationItems,
                    navController = navController
                )
            },
        ) { paddingValues ->
            Surface(Modifier.padding(paddingValues)) {
                NavHost(navController = navController, startDestination = ROUTE_DEFAULT) {
                    navigationItems.forEach { item ->
                        composable(item.route) {
                            item.content()
                        }
                    }
                }
            }
        }

    }
}

@ExperimentalMaterial3Api
@Composable
private fun TopBar(navigationViewModel: NavigationViewModel = viewModel()) {
    SmallTopAppBar(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)),
        title = { Text(navigationViewModel.activeNavigationItem.title()) },
        navigationIcon = {
            Icon(
                painter = navigationViewModel.activeNavigationItem.icon(),
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 8.dp).size(32.dp)
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent, //Already handled by modifier above, which draws behind system bar
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
    )
}

@Composable
private fun BottomBar(
    navigationItems: List<NavigationItem>,
    navController: NavController,
    navigationViewModel: NavigationViewModel = viewModel(),
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(NavigationBarDefaults.Elevation))
            .navigationBarsPadding()
    ) {
        // Because we have 5 items which is more than material recommends the titles don't fit with default item padding
        // Still breaks on the smallest screens when text is more than 7m wide, but that can't be helped
        FlexibleNavigationBar(itemPadding = 0.dp) {
            navigationItems.forEach { item ->
                val selected = item == navigationViewModel.activeNavigationItem
                NavigationBarItem(
                    selected = selected,
                    alwaysShowLabel = false,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(painter = item.icon(), contentDescription = null)
                    },
                    label = {
                        Text(item.title())
                    },
                )
            }
        }
    }
}

@Composable
private fun TintSystemBars() {
    val statusBarColor = MaterialTheme.colorScheme.primary
    val darkStatusIcons = remember { statusBarColor.luminance() > 0.5 }
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
    LaunchedEffect(darkStatusIcons, darkNavigationIcons, navbarShimColor) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = darkStatusIcons
        )
        systemUiController.setNavigationBarColor(
            color = navbarShimColor,
            darkIcons = darkNavigationIcons
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Translations {
        MainContent()
    }
}