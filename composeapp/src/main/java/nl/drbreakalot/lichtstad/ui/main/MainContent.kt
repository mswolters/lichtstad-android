package nl.drbreakalot.lichtstad.ui.main

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import nl.drbreakalot.lichtstad.ui.component.FlexibleNavigationBar
import nl.drbreakalot.lichtstad.ui.theme.LichtstadTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    navigationViewModel: NavigationViewModel = koinViewModel(),
    navigationItems: List<NavigationItem> = NAVIGATION_ITEMS
) {
    val navController = rememberNavController()
    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { controller, destination, bundle ->
            navigationViewModel.activeNavigationItem = navigationItems.single { item -> destination.hierarchy.any { it.route == item.route } }
        }
    }
    LichtstadTheme(
        navigationViewModel = navigationViewModel,
    ) {
        TintSystemBars()

        // Pinned keeps the title always visible, switch to enterAlways to make it hide,
        // once the bug that keeps the title centered while hiding is fixed.
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        // TODO: Somehow make TopAppBar appear when switching content
        Scaffold(
            modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = { TopBar(scrollBehavior = scrollBehavior) },
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
private fun TopBar(navigationViewModel: NavigationViewModel = koinViewModel(), scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(
        title = { Text(navigationViewModel.activeNavigationItem.title()) },
        navigationIcon = {
            Icon(
                painter = navigationViewModel.activeNavigationItem.icon(),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(32.dp)
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        scrollBehavior = scrollBehavior,
    )
}

@Composable
private fun BottomBar(
    navigationItems: List<NavigationItem>,
    navController: NavController,
    navigationViewModel: NavigationViewModel = koinViewModel(),
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
    val navigationBarColor = MaterialTheme.colorScheme.surfaceColorAtElevation(NavigationBarDefaults.Elevation)
    val darkNavigationIcons = remember { navigationBarColor.luminance() > 0.5 }
    val systemUiController = rememberSystemUiController()

    // In portrait, we let the bottom nav handle the navigation bar color
    // In landscape, we need a shim or the buttons become invisible
    val shouldApplyLandscapeScrim = LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE
    val navbarShimColor = if (shouldApplyLandscapeScrim)
        if (darkNavigationIcons) {
            navigationBarColor.copy(alpha = 0.5f)
        } else {
            Color.Black.copy(alpha = 0.3f)
        }
    else Color.Transparent
    val transformForLightContent = { _: Color -> Color.Black.copy(alpha = 0.3f) }
    LaunchedEffect(darkStatusIcons, darkNavigationIcons, navbarShimColor) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = darkStatusIcons,
            transformColorForLightContent = transformForLightContent
        )
        systemUiController.setNavigationBarColor(
            color = navbarShimColor,
            darkIcons = darkNavigationIcons,
            navigationBarContrastEnforced = false,
            transformColorForLightContent = transformForLightContent
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainContent()
}